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
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.example.learn.http.client.apache.hc4.util.HttpClientUtils;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * 发送不同类型的content-type
 */
public class Ch03RequestParameterTest {

    @Before
    public void setup() {
        HttpClientUtils.initLog();
    }

    // application/x-www-form-urlencoded类型的post请求
    @Test
    public void test01() throws IOException {
        try (CloseableHttpClient httpclient = HttpClients.createDefault()) {
            // 创建HttpPost实例，设置目标URL
            HttpPost httpPost = new HttpPost("http://httpbin.org/anything");

            // 创建参数列表
            List<NameValuePair> params = new ArrayList<>();
            params.add(new BasicNameValuePair("key1", "value1"));
            params.add(new BasicNameValuePair("key2", "中文"));

            // 设置请求参数为application/x-www-form-urlencoded格式
            httpPost.setEntity(new UrlEncodedFormEntity(params, StandardCharsets.UTF_8));

            // 执行请求并获取响应
            try (CloseableHttpResponse response = httpclient.execute(httpPost)) {
                System.out.println(response.getStatusLine());
                HttpEntity responseEntity = response.getEntity();
                // 不设置编码,就依据HttpEntity的ContentType的mimeType来决定,
                // 如果还不确定,就用http默认编码ISO_8859_1
                // application/json类型mimeType的编码是UTF-8
                String responseBody = EntityUtils.toString(responseEntity);

                // 注意:返回的json数据中,中文用的是\u4e2d\u6587
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

            // 通过MultipartEntityBuilder来创建MultipartFormEntity
            MultipartEntityBuilder builder = MultipartEntityBuilder.create();

            // 添加普通字段
            builder.addTextBody("field1", "value1", ContentType.TEXT_PLAIN); // 不设置编码就用text/plain的编码ISO_8859_1
            builder.addTextBody("field2", URLEncoder.encode("中文", StandardCharsets.UTF_8.name()), ContentType.APPLICATION_FORM_URLENCODED);
            builder.addTextBody("field3", "{\"name\": \"zhangsan\"}", ContentType.APPLICATION_JSON);

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

    @Test
    public void test11() throws IOException {
        try (CloseableHttpClient httpclient = HttpClients.createDefault()) {
            // 创建HttpPost实例，设置目标URL
            HttpPost httpPost = new HttpPost("http://localhost:8080/myapp/param/multipart");
//            httpPost = new HttpPost("http://localhost:8080/myapp/param/multipart2");

            // 通过MultipartEntityBuilder来创建MultipartFormEntity
            MultipartEntityBuilder builder = MultipartEntityBuilder.create();

            // 添加普通字段
            builder.addTextBody("field1", "value1", ContentType.TEXT_PLAIN); // 不设置编码就用text/plain的编码ISO_8859_1
            builder.addTextBody("field2", URLEncoder.encode("中文", StandardCharsets.UTF_8.name()), ContentType.APPLICATION_FORM_URLENCODED);
            builder.addTextBody("field3", "{\"name\": \"zhangsan\"}", ContentType.APPLICATION_JSON);

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
