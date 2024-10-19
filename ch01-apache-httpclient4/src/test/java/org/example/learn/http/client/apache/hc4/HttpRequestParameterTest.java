package org.example.learn.http.client.apache.hc4;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 发送不同类型的content-type
 */
public class HttpRequestParameterTest {

    // application/x-www-form-urlencoded类型的post请求
    @Test
    public void test01() throws IOException {
        try (CloseableHttpClient httpclient = HttpClients.createDefault()) {
            // 创建HttpPost实例，设置目标URL
            HttpPost httpPost = new HttpPost("http://httpbin.org/anything");

            // 创建参数列表
            List<NameValuePair> params = new ArrayList<>();
            params.add(new BasicNameValuePair("key1", "value1"));
            params.add(new BasicNameValuePair("key2", "value2"));

            // 设置请求参数为application/x-www-form-urlencoded格式
            httpPost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));

            // 执行请求并获取响应
            try (CloseableHttpResponse response = httpclient.execute(httpPost)) {
                System.out.println(response.getStatusLine());

                String responseBody = EntityUtils.toString(response.getEntity());
                System.out.println(responseBody);
            }
        }
    }

    // multipart/form-data类型的post请求
    @Test
    public void test10() throws IOException {
        try (CloseableHttpClient httpclient = HttpClients.createDefault()) {
            // 创建HttpPost实例，设置目标URL
            HttpPost httpPost = new HttpPost("http://httpbin.org/anything");

            // 创建MultipartEntityBuilder实例
            MultipartEntityBuilder builder = MultipartEntityBuilder.create();

            // 添加普通字段
            builder.addTextBody("field1", "value1", ContentType.TEXT_PLAIN);
            builder.addTextBody("field2", "中文", ContentType.TEXT_PLAIN);

            String cwd = System.getProperty("user.dir");
            System.out.println("cwd = " + cwd);
            System.out.println("----------------------------------------");

            // 添加文件字段
            File file = new File("src/test/resources/文件名.txt");
            builder.addBinaryBody("file", file, ContentType.APPLICATION_OCTET_STREAM, file.getName());

            // 构建HttpEntity
            HttpEntity multipart = builder.build();

            // 将HttpEntity设置到HttpPost实例中
            httpPost.setEntity(multipart);

            // 执行请求并获取响应
            try (CloseableHttpResponse response = httpclient.execute(httpPost)) {
                System.out.println(response.getStatusLine());

                String responseBody = EntityUtils.toString(response.getEntity());
                System.out.println(responseBody);
            }
        }
    }
}
