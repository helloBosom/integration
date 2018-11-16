package fun.peri.utils.elasticsearch;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.collections.map.HashedMap;
import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.action.admin.indices.create.CreateIndexRequest;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexResponse;
import org.elasticsearch.action.admin.indices.exists.indices.IndicesExistsRequest;
import org.elasticsearch.action.admin.indices.exists.indices.IndicesExistsResponse;
import org.elasticsearch.action.admin.indices.mapping.put.PutMappingRequest;
import org.elasticsearch.action.bulk.BulkRequestBuilder;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.client.Requests;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.settings.Settings.Builder;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.aggregations.AggregationBuilder;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.sort.SortOrder;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class ESHelper {

	private static final Logger logger = LoggerFactory.getLogger(ESHelper.class);

	private final static int MAX = 1000;
	
	private TransportClient client;
	
	public ESHelper(String clusterNodes, String clusterName){
		
		Settings settings = Settings.builder().put("cluster.name", clusterName).build();
		client = new PreBuiltTransportClient(settings);
		try {
			if (!"".equals(clusterNodes)) {
				for (String nodes : clusterNodes.split(",")) {
					String InetSocket[] = nodes.split(":");
					String address = InetSocket[0];
					Integer port = Integer.valueOf(InetSocket[1]);
					client.addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName(address), port));
				}
			}
		} catch (Exception e) {
			logger.error("初始化elasticsearch 搜索引擎配置失败");
		}
		
	}
	
	public void closeConnect(){
		client.close();
	}

	/**
	 * 功能描述：新建索引
	 * 
	 * @param indexName
	 *            索引名
	 */
	public void createIndex(String indexName) {
		client.admin().indices().create(new CreateIndexRequest(indexName)).actionGet();
	}

	/**
	 * 功能描述：新建索引
	 * 
	 * @param index
	 *            索引名
	 * @param type
	 *            类型
	 */
	public void createIndex(String index, String type) {
		client.prepareIndex(index, type).setSource().get();
	}
	
	/**
	 * 功能描述：新建索引
	 * 
	 * @param indexs
	 *            索引实体类
	 */
	public void createIndex(List<Index> indexs) {
					
		for(Index index : indexs){  
			if(StringUtils.isBlank(index.getIndex())){
				logger.error("createIndex error, indexName is not null!!");
				continue;
			}
			
			if(StringUtils.isBlank(index.getType())){
				logger.error("createIndex error, type is not null!!");
				continue;
			}
			
			if(index.getNumber_of_shards() < 1){
				logger.error("createIndex error, numberOfshards is too small !!");
				continue;
			}
			
			
			if(StringUtils.isBlank(index.getFieldJson())){
				logger.error("createIndex error, fieldJson is not null!!");
				continue;
			}
            if (indexExist(index.getIndex())) {  
                continue;  
            }  
            
            createIndex(index.getIndex(), index.getType(), index.getNumber_of_shards(), index.getNumber_of_replicas(), index.getFieldJson());
            
		}    
		
		
		
	}

	
	
	/**
	 * 功能描述：新建索引
	 * 
	 * @param indexName
	 *            索引名
	 * @param type
	 *            类型
	 * @param numberOfshards
	 *            分片数
	 * @param numberOfreplicas
	 *            备份片数
	 * @param fieldJson
	 *            数据结构
	 */
	public void createIndex(String indexName, String type , int numberOfshards, int numberOfreplicas, String fieldJson) {
		
		if(StringUtils.isBlank(indexName)){
			logger.error("createIndex error, indexName is not null!!");
		}
		
		if(StringUtils.isBlank(type)){
			logger.error("createIndex error, type is not null!!");
		}
		
		if(numberOfshards < 1){
			logger.error("createIndex error, numberOfshards is too small !!");
		}
		
		if(StringUtils.isBlank(fieldJson)){
			logger.error("createIndex error, fieldJson is not null!!");
		}
				
		
		Builder builder = Settings.builder().put("index.number_of_shards" , numberOfshards).put("index.number_of_replicas" , numberOfreplicas);
		client.admin().indices().create(new CreateIndexRequest(indexName, builder.build())).actionGet();
		PutMappingRequest mapping = Requests.putMappingRequest(indexName).type(type).source(fieldJson);
		client.admin().indices().putMapping(mapping).actionGet();
		
		
	}

	/**
	 * 功能描述：删除索引
	 * 
	 * @param index
	 *            索引名
	 */
	public boolean deleteIndex(String index) {
		boolean flag = false;
		if (indexExist(index)) {
			DeleteIndexResponse dResponse = client.admin().indices().prepareDelete(index).execute().actionGet();
			flag = true;
			if (!dResponse.isAcknowledged()) {
				logger.error("failed to delete index.");
			}
		} else {
			logger.error("index name not exists");
		}
		return flag;
	}

	/**
	 * 功能描述：插入数据
	 * 
	 * @param index
	 *            索引名
	 * @param type
	 *            类型
	 * @param json
	 *            数据
	 */
	public void insertData(String index, String type, String json) {
		IndexResponse response = client.prepareIndex(index, type).setSource(json).get();
	}

	/**
	 * 功能描述：插入数据
	 * 
	 * @param index
	 *            索引名
	 * @param type
	 *            类型
	 * @param _id
	 *            数据id
	 * @param json
	 *            数据
	 */
	public void insertData(String index, String type, String _id, String json) {
		IndexResponse response = client.prepareIndex(index, type).setId(_id).setSource(json).get();
	}

	/**
	 * 功能描述：更新数据
	 * 
	 * @param index
	 *            索引名
	 * @param type
	 *            类型
	 * @param _id
	 *            数据id
	 * @param json
	 *            数据
	 */
	public void updateData(String index, String type, String _id, String json) throws Exception {
		try {
			UpdateRequest updateRequest = new UpdateRequest(index, type, _id).doc(json);
			client.update(updateRequest).get();
		} catch (Exception e) {
			logger.error("update data failed.", e);
		}
	}

	/**
	 * 功能描述：删除数据
	 * 
	 * @param index
	 *            索引名
	 * @param type
	 *            类型
	 * @param _id
	 *            数据id
	 */
	public void deleteData(String index, String type, String _id) {
		DeleteResponse response = client.prepareDelete(index, type, _id).get();
	}

	/**
	 * 功能描述：批量插入数据
	 * 
	 * @param index
	 *            索引名
	 * @param type
	 *            类型
	 * @param data
	 *            (_id 主键, json 数据)
	 */
	public void bulkInsertData(String index, String type, Map<String, String> data) {
		BulkRequestBuilder bulkRequest = client.prepareBulk();
		for (Map.Entry<String, String> entry : data.entrySet()) {
			bulkRequest.add(client.prepareIndex(index, type, entry.getKey()).setSource(entry.getValue()));
		}
		BulkResponse bulkResponse = bulkRequest.get();
	}

	/**
	 * 功能描述：批量插入数据
	 * 
	 * @param index
	 *            索引名
	 * @param type
	 *            类型
	 * @param jsonList
	 *            批量数据
	 */
	public void bulkInsertData(String index, String type, List<String> jsonList) {
		BulkRequestBuilder bulkRequest = client.prepareBulk();

		for (String item : jsonList) {
			bulkRequest.add(client.prepareIndex(index, type).setSource(item));
		}
		BulkResponse bulkResponse = bulkRequest.get();
	}

	/**
	 * 功能描述：查询
	 * 
	 * @param index
	 *            索引名
	 * @param type
	 *            类型
	 * @param constructor
	 *            查询构造
	 */
	public List<Map<String, Object>> search(String index, String type, ESQueryBuilderConstructor constructor) {
		List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
		SearchRequestBuilder searchRequestBuilder = client.prepareSearch(index).setTypes(type);
		// 排序
		if (StringUtils.isNotEmpty(constructor.getAsc()))
			searchRequestBuilder.addSort(constructor.getAsc(), SortOrder.ASC);
		if (StringUtils.isNotEmpty(constructor.getDesc()))
			searchRequestBuilder.addSort(constructor.getDesc(), SortOrder.DESC);
		// 设置查询体
		searchRequestBuilder.setQuery(constructor.listBuilders());
		// 返回条目数
		int size = constructor.getSize();
		if (size < 0) {
			size = 0;
		}
		if (size > MAX) {
			size = MAX;
		}
		// 返回条目数
		searchRequestBuilder.setSize(size);

		searchRequestBuilder.setFrom(constructor.getFrom() < 0 ? 0 : constructor.getFrom());

		SearchResponse searchResponse = searchRequestBuilder.execute().actionGet();

		SearchHits hits = searchResponse.getHits();
		SearchHit[] searchHists = hits.getHits();
		for (SearchHit sh : searchHists) {
			result.add(sh.getSource());
		}

		return result;
	}

	/**
	 * 按条件分页查询
	 * @param index
	 * @param type
	 * @param constructor
	 * @return
	 */
	public JSONObject searchpage(String index, String type, ESQueryBuilderConstructor constructor) {
		JSONObject re = new JSONObject();
		List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
		SearchRequestBuilder searchRequestBuilder = client.prepareSearch(index).setTypes(type);
		// 排序
		if (StringUtils.isNotEmpty(constructor.getAsc()))
			searchRequestBuilder.addSort(constructor.getAsc(), SortOrder.ASC);
		if (StringUtils.isNotEmpty(constructor.getDesc()))
			searchRequestBuilder.addSort(constructor.getDesc(), SortOrder.DESC);
		// 设置查询体
		searchRequestBuilder.setQuery(constructor.listBuilders());
		// 返回条目数
		int size = constructor.getSize();
		if (size < 0) {
			size = 0;
		}
		if (size > MAX) {
			size = MAX;
		}
		// 返回条目数
		searchRequestBuilder.setSize(size);

		searchRequestBuilder.setFrom(constructor.getFrom() < 0 ? 0 : constructor.getFrom());

		SearchResponse searchResponse = searchRequestBuilder.execute().actionGet();

		SearchHits hits = searchResponse.getHits();
		SearchHit[] searchHists = hits.getHits();
		for (SearchHit sh : searchHists) {
			result.add(sh.getSource());
		}

		//匹配查询条件的总条数
		Long total = searchResponse.getHits().getTotalHits();
        //匹配查询条件的总页数
		int totalpages = (int) (total%size==0?total/size:total/size+1);
		JSONObject pageBean = new JSONObject();

		return re;
	}

	/**
	 * 功能描述：统计查询
	 * 
	 * @param index
	 *            索引名
	 * @param type
	 *            类型
	 * @param constructor
	 *            查询构造
	 * @param groupBy
	 *            统计字段
	 */
	public Map<Object, Object> statSearch(String index, String type, ESQueryBuilderConstructor constructor,
			String groupBy) {
		Map<Object, Object> map = new HashedMap();
		SearchRequestBuilder searchRequestBuilder = client.prepareSearch(index).setTypes(type);
		// 排序
		if (StringUtils.isNotEmpty(constructor.getAsc()))
			searchRequestBuilder.addSort(constructor.getAsc(), SortOrder.ASC);
		if (StringUtils.isNotEmpty(constructor.getDesc()))
			searchRequestBuilder.addSort(constructor.getDesc(), SortOrder.DESC);
		// 设置查询体
		if (null != constructor) {
			searchRequestBuilder.setQuery(constructor.listBuilders());
		} else {
			searchRequestBuilder.setQuery(QueryBuilders.matchAllQuery());
		}
		int size = constructor.getSize();
		if (size < 0) {
			size = 0;
		}
		if (size > MAX) {
			size = MAX;
		}
		// 返回条目数
		searchRequestBuilder.setSize(size);

		searchRequestBuilder.setFrom(constructor.getFrom() < 0 ? 0 : constructor.getFrom());
		SearchResponse sr = searchRequestBuilder.addAggregation(AggregationBuilders.terms("agg").field(groupBy)).get();

		Terms stateAgg = sr.getAggregations().get("agg");
		Iterator<Terms.Bucket> iter = stateAgg.getBuckets().iterator();

		while (iter.hasNext()) {
			Terms.Bucket gradeBucket = iter.next();
			map.put(gradeBucket.getKey(), gradeBucket.getDocCount());
		}

		return map;
	}

	/**
	 * 功能描述：统计查询
	 * 
	 * @param index
	 *            索引名
	 * @param type
	 *            类型
	 * @param constructor
	 *            查询构造
	 * @param agg
	 *            自定义计算
	 * 
	 *            自定义查询 举例 1： select team, count(*) as player_count from player
	 *            group by team;ES的java api： TermsBuilder teamAgg=
	 *            AggregationBuilders.terms("player_count ").field("team");
	 *            sbuilder.addAggregation(teamAgg); SearchResponse response =
	 *            sbuilder.execute().actionGet();
	 * 
	 * 
	 *            举例2：select team, position, count(*) as pos_count from player
	 *            group by team, position; TermsBuilder teamAgg=
	 *            AggregationBuilders.terms("player_count ").field("team");
	 *            TermsBuilder posAgg=
	 *            AggregationBuilders.terms("pos_count").field("position");
	 *            sbuilder.addAggregation(teamAgg.subAggregation(posAgg));
	 * 
	 *            举例3：select team, max(age) as max_age from player group by
	 *            team; TermsBuilder teamAgg=
	 *            AggregationBuilders.terms("player_count ").field("team");
	 *            MaxBuilder ageAgg=
	 *            AggregationBuilders.max("max_age").field("age");
	 *            sbuilder.addAggregation(teamAgg.subAggregation(ageAgg));
	 */
	public Map<Object, Object> statSearch(String index, String type, ESQueryBuilderConstructor constructor,
			AggregationBuilder agg) {
		if (agg == null) {
			return null;
		}
		Map<Object, Object> map = new HashedMap();
		SearchRequestBuilder searchRequestBuilder = client.prepareSearch(index).setTypes(type);
		// 排序
		if (StringUtils.isNotEmpty(constructor.getAsc()))
			searchRequestBuilder.addSort(constructor.getAsc(), SortOrder.ASC);
		if (StringUtils.isNotEmpty(constructor.getDesc()))
			searchRequestBuilder.addSort(constructor.getDesc(), SortOrder.DESC);
		// 设置查询体
		if (null != constructor) {
			searchRequestBuilder.setQuery(constructor.listBuilders());
		} else {
			searchRequestBuilder.setQuery(QueryBuilders.matchAllQuery());
		}
		int size = constructor.getSize();
		if (size < 0) {
			size = 0;
		}
		if (size > MAX) {
			size = MAX;
		}
		// 返回条目数
		searchRequestBuilder.setSize(size);

		searchRequestBuilder.setFrom(constructor.getFrom() < 0 ? 0 : constructor.getFrom());
		SearchResponse sr = searchRequestBuilder.addAggregation(agg).get();

		Terms stateAgg = sr.getAggregations().get("agg");
		Iterator<Terms.Bucket> iter = stateAgg.getBuckets().iterator();

		while (iter.hasNext()) {
			Terms.Bucket gradeBucket = iter.next();
			map.put(gradeBucket.getKey(), gradeBucket.getDocCount());
		}

		return map;
	}

	public void test() {
		String json = "{" + "\"user\":\"kimchy1\"," + "\"postDate\":\"2013-01-30\","
				+ "\"message\":\"trying out Elasticsearch\"" + "}";
		IndexResponse response = client.prepareIndex("wangwei", "test").setSource(json).get();
	}

	/**
	 * 功能描述：验证索引是否存在
	 * 
	 * @param index
	 *            索引名
	 */
	public boolean indexExist(String index) {
		IndicesExistsRequest inExistsRequest = new IndicesExistsRequest(index);
		IndicesExistsResponse inExistsResponse = client.admin().indices().exists(inExistsRequest).actionGet();
		return inExistsResponse.isExists();
	}

	public void close() {
		client.close();
	}

}
