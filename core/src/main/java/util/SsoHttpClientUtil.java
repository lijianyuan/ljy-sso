package util;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Description: http工具类
 * @author: ljy
 * @date: 2021年06月02日 10:46
 * @email 15810874514@163.com
 */

public class SsoHttpClientUtil {

    private static Logger logger = LoggerFactory.getLogger(SsoHttpClientUtil.class);
    public static String post(String url, Map<String, String> params, Map<String, String> headers){
        HttpPost httpPost = null;
        CloseableHttpClient httpClient = null;
        try{
            httpPost = new HttpPost(url);
            if (params != null && !params.isEmpty()) {
                List<NameValuePair> formParams = new ArrayList<NameValuePair>();
                for(Map.Entry<String,String> entry : params.entrySet()){
                    formParams.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
                }
                httpPost.setEntity(new UrlEncodedFormEntity(formParams, "UTF-8"));
            }
            RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(5000).setConnectTimeout(5000).build();
            httpPost.setConfig(requestConfig);
            // headers
            if (headers!=null && headers.size()>0) {
                for (Map.Entry<String, String> headerItem: headers.entrySet()) {
                    httpPost.setHeader(headerItem.getKey(), headerItem.getValue());
                }
            }
            httpClient = HttpClients.custom().disableAutomaticRetries().build();
            HttpResponse response = httpClient.execute(httpPost);
            HttpEntity entity = response.getEntity();
            if (null != entity) {
                if (response.getStatusLine().getStatusCode() == 200) {
                    String responseMsg = EntityUtils.toString(entity, "UTF-8");
                    EntityUtils.consume(entity);
                    return responseMsg;
                }
                EntityUtils.consume(entity);
            }
            logger.info("http的statusCode:" + response.getStatusLine().getStatusCode());
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        } finally{
            if (httpPost!=null) {
                httpPost.releaseConnection();
            }
            if (httpClient!=null) {
                try {
                    httpClient.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

    