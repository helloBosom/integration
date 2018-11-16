package fun.peri.utils.elasticsearch;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class InitIndexFile {
	
	private static final Logger logger = LoggerFactory.getLogger(InitIndexFile.class);
	
	/** 
     * 读取json配置文件 
     * @return JSONArray 
     * @throws IOException 
     */  
    public JSONArray readJosnFile() throws IOException{
        InputStream is = this.getClass().getClassLoader().getResourceAsStream("index.json");  
        BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF-8"));  
        StringBuffer sb = new StringBuffer();  
        String tmp = null;  
        while((tmp=br.readLine()) != null){  
            sb.append(tmp);  
        }  
        JSONArray result = JSONArray.parseArray(sb.toString());
        return result;  
    }
    
    
    
    /** 
     * 获取要创建的index列表 
     * @return List<Index>
     */  
    public List<Index> getIndexList(){  
          
        List<Index> result = new ArrayList<Index>();  
        JSONArray jsonArray = null;
        try {  
            jsonArray = readJosnFile();  
        } catch (IOException e) {  
        	logger.error("readJsonFile exception!",e);
        }  
        if (jsonArray == null || jsonArray.size() == 0) {  
            return null;  
        }  
        for (int i = 0; i < jsonArray.size(); i++) {  
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            Index indexObject = new Index();  
            String index = jsonObject.getString("index");  
            String type = jsonObject.getString("type");  
            Integer number_of_shards = jsonObject.getInteger("number_of_shards");  
            Integer number_of_replicas = jsonObject.getInteger("number_of_replicas");  
            String fieldRType = jsonObject.get("fieldType").toString();  
            indexObject.setIndex(index);  
            indexObject.setType(type);  
            indexObject.setFieldJson(fieldRType);  
            indexObject.setNumber_of_shards(number_of_shards);  
            indexObject.setNumber_of_replicas(number_of_replicas);  
            result.add(indexObject);  
        }  
        return result;  
    }  
    
	

}
