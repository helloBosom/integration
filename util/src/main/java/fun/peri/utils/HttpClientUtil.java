package fun.peri.utils;

import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.CookieStore;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.*;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.net.URI;
import java.util.Map;

public class HttpClientUtil {

    static CookieStore cookieStore = null;
    static String JSESSIONID;


    /**
     * 将cookie保存到静态变量中供后续调用
     *
     * @param httpResponse
     */
    public static void getJsessionId(HttpResponse httpResponse) {
        cookieStore = new BasicCookieStore();
        // JSESSIONID
        String setCookie = httpResponse.getFirstHeader("Set-Cookie").getValue();
        JSESSIONID = setCookie.substring("session-id=".length());
    }

    public static JSONObject doPost(String postUrl, JSONObject jsonObject, Map<String, String> params) throws Exception {
        String retStr = "";
        // 创建HttpClientBuilder
        HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
        // HttpClient
        CloseableHttpClient closeableHttpClient = httpClientBuilder.build();
        HttpPost httpPost = new HttpPost(postUrl);
        // 设置请求和传输超时时间
        RequestConfig requestConfig = RequestConfig.custom()
                .setSocketTimeout(30000).setConnectTimeout(30000).build();
        httpPost.setConfig(requestConfig);


        CloseableHttpResponse response;

        if (null != jsonObject) {
            StringEntity stringEntity = new StringEntity(jsonObject.toJSONString(), "UTF-8");
            stringEntity.setContentEncoding("UTF-8");
            httpPost.setEntity(stringEntity);
        }

        if (null != params) {
            for (Map.Entry<String, String> entry : params.entrySet()) {
                httpPost.addHeader(entry.getKey(), entry.getValue());
            }
        }

        if (null != JSESSIONID) {
            httpPost.addHeader("Cookie", "session-id=" + JSESSIONID);
            response = closeableHttpClient.execute(httpPost);
        } else {
            response = closeableHttpClient.execute(httpPost);
            getJsessionId(response);
        }


        HttpEntity httpEntity = response.getEntity();
        retStr = EntityUtils.toString(httpEntity, "UTF-8");
        System.out.println(retStr);
        // 释放资源
        closeableHttpClient.close();

        return JSONObject.parseObject(retStr);
    }

    public static JSONObject doGet(String getUrl, Map<String, String> params) throws Exception {
        String retStr = "";
        // 创建HttpClientBuilder
        HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
        // HttpClient
        CloseableHttpClient closeableHttpClient = httpClientBuilder.build();
        HttpGet httpGet = new HttpGet(getUrl);
        // 设置请求和传输超时时间
        RequestConfig requestConfig = RequestConfig.custom()
                .setSocketTimeout(30000).setConnectTimeout(30000).build();
        httpGet.setConfig(requestConfig);


        if (null != params) {
            for (Map.Entry<String, String> entry : params.entrySet()) {
                httpGet.addHeader(entry.getKey(), entry.getValue());
            }
        }

        if (null != JSESSIONID) {
            httpGet.setHeader("Cookie", "session-id=" + JSESSIONID);
        }

        CloseableHttpResponse response = closeableHttpClient.execute(httpGet);

        HttpEntity httpEntity = response.getEntity();
        retStr = EntityUtils.toString(httpEntity, "UTF-8");
        System.out.println(retStr);
        // 释放资源
        closeableHttpClient.close();

        return JSONObject.parseObject(retStr);
    }

    public static JSONObject doPut(String putUrl, JSONObject jsonObject, Map<String, String> params) throws Exception {
        String retStr = "";
        // 创建HttpClientBuilder
        HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
        // HttpClient
        CloseableHttpClient closeableHttpClient = httpClientBuilder.build();
        HttpPut httpPut = new HttpPut(putUrl);
        // 设置请求和传输超时时间
        RequestConfig requestConfig = RequestConfig.custom()
                .setSocketTimeout(30000).setConnectTimeout(30000).build();
        httpPut.setConfig(requestConfig);


        if (null != jsonObject) {
            StringEntity stringEntity = new StringEntity(jsonObject.toJSONString(), "UTF-8");
            stringEntity.setContentEncoding("UTF-8");
            httpPut.setEntity(stringEntity);
        }

        if (null != params) {
            for (Map.Entry<String, String> entry : params.entrySet()) {
                httpPut.addHeader(entry.getKey(), entry.getValue());
            }
        }

        if (null != JSESSIONID) {
            httpPut.addHeader("Cookie", "session-id=" + JSESSIONID);
        }

        CloseableHttpResponse response = closeableHttpClient.execute(httpPut);

        HttpEntity httpEntity = response.getEntity();
        retStr = EntityUtils.toString(httpEntity, "UTF-8");
        System.out.println(retStr);
        // 释放资源
        closeableHttpClient.close();

        return JSONObject.parseObject(retStr);
    }


    public static JSONObject doDelete(String deleteUrl, JSONObject jsonObject, Map<String, String> params) throws Exception {

        class httpDeleteWithBody extends HttpEntityEnclosingRequestBase{

            public static final String METHOD_NAME = "DELETE";

            @SuppressWarnings("unused")
            public httpDeleteWithBody() {
            }

            @SuppressWarnings("unused")
            public httpDeleteWithBody(URI uri) {
                setURI(uri);
            }

            public httpDeleteWithBody(String uri) {
                setURI(URI.create(uri));
            }

            public String getMethod() {
                return METHOD_NAME;
            }
        }

        String retStr = "";
        // 创建HttpClientBuilder
        HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
        // HttpClient
        CloseableHttpClient closeableHttpClient = httpClientBuilder.build();
        //HttpDelete httpDelete = new HttpDelete(deleteUrl);
        httpDeleteWithBody httpDelete = new httpDeleteWithBody(deleteUrl);
        // 设置请求和传输超时时间
        RequestConfig requestConfig = RequestConfig.custom()
                .setSocketTimeout(30000).setConnectTimeout(30000).build();
        httpDelete.setConfig(requestConfig);

        if (null != jsonObject) {
            StringEntity stringEntity = new StringEntity(jsonObject.toJSONString(), "UTF-8");
            stringEntity.setContentEncoding("UTF-8");
            httpDelete.setEntity(stringEntity);
        }


        if (null != params) {
            for (Map.Entry<String, String> entry : params.entrySet()) {
                httpDelete.addHeader(entry.getKey(), entry.getValue());
            }
        }

        if (null != JSESSIONID) {
            httpDelete.addHeader("Cookie", "session-id=" + JSESSIONID);
        }

        CloseableHttpResponse response = closeableHttpClient.execute(httpDelete);

        HttpEntity httpEntity = response.getEntity();
        retStr = EntityUtils.toString(httpEntity, "UTF-8");
        System.out.println(retStr);
        // 释放资源
        closeableHttpClient.close();

        return JSONObject.parseObject(retStr);
    }


}
