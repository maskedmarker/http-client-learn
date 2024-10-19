package org.example.learn.http.client.apache.hc4;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

/**
 * 打印日志的系统参数
 * -Dorg.apache.commons.logging.Log=org.apache.commons.logging.impl.SimpleLog
 * -Dorg.apache.commons.logging.simplelog.showdatetime=true
 * -Dorg.apache.commons.logging.simplelog.log.org.apache.http=DEBUG
 * -Dorg.apache.commons.logging.simplelog.log.org.apache.http.wire=ERROR
 *
 *
 */
public class HttpClientTest {

    public static void initLog() {
        System.setProperty("org.apache.commons.logging.Log", "org.apache.commons.logging.impl.SimpleLog");
        System.setProperty("org.apache.commons.logging.simplelog.showdatetime", "true");
        System.setProperty("org.apache.commons.logging.simplelog.log.org.apache.http", "DEBUG");
        System.setProperty("org.apache.commons.logging.simplelog.log.org.apache.http.wire", "ERROR");
    }

    @Test
    public void test01() throws IOException {
        initLog();

        try (CloseableHttpClient httpclient = HttpClients.createDefault()) {
            HttpGet httpGet = new HttpGet("https://www.example.com/");

            // The underlying HTTP connection is still held by the response object
            // to allow the response content to be streamed directly from the network socket.
            // In order to ensure correct deallocation of system resources
            // the user MUST call CloseableHttpResponse#close() from a finally clause.
            // Please note that if response content is not fully consumed the underlying
            // connection cannot be safely re-used and will be shut down and discarded
            // by the connection manager.
            try (CloseableHttpResponse response = httpclient.execute(httpGet)) {
                System.out.println(response.getStatusLine());
                HttpEntity entity = response.getEntity();
                // 如果无法从Content-Type header中获取到编码信息,就用http的默认编码ISO_8859_1
                String responseBody = EntityUtils.toString(entity);
                System.out.println("responseBody = " + responseBody);
            }
        }
    }

    @Test
    public void test02() throws IOException {
        initLog();

        try (CloseableHttpClient httpclient = HttpClients.createDefault()) {
            HttpPost httpPost = new HttpPost("https://httpbin.org/post");
            List<NameValuePair> nvps = new ArrayList<>();
            nvps.add(new BasicNameValuePair("username", "vip"));
            nvps.add(new BasicNameValuePair("password", "secret"));
            httpPost.setEntity(new UrlEncodedFormEntity(nvps));

            try (CloseableHttpResponse response = httpclient.execute(httpPost)) {
                HttpEntity entity = response.getEntity();
                String responseBody = EntityUtils.toString(entity);
                System.out.println("responseBody = " + responseBody);
            }
        }
    }

    @Test
    public void test10() throws IOException {
        try (CloseableHttpClient httpclient = HttpClients.createDefault()) {
            HttpPost httpPost = new HttpPost("https://httpbin.org/post");
            List<NameValuePair> nvps = new ArrayList<>();
            nvps.add(new BasicNameValuePair("username", "vip"));
            nvps.add(new BasicNameValuePair("password", "secret"));
            httpPost.setEntity(new UrlEncodedFormEntity(nvps));

            System.out.println("-------request line----------");
            System.out.println(httpPost.getRequestLine());
            System.out.println("-------all headers----------");
            Arrays.stream(httpPost.getAllHeaders()).forEach(System.out::println);
            System.out.println("--------entity---------");
            System.out.println(EntityUtils.toString(httpPost.getEntity()));
            System.out.println("-------end of entity----------");

            try (CloseableHttpResponse response = httpclient.execute(httpPost)) {
                StatusLine statusLine = response.getStatusLine();
                Header[] allHeaders = response.getAllHeaders();
                String responseBody = EntityUtils.toString(response.getEntity());
                System.out.println("=======status line==========");
                System.out.println(statusLine);
                System.out.println("========all headers=========");
                Arrays.stream(allHeaders).forEach(System.out::println);
                System.out.println("========entity=========");
                System.out.println(responseBody);
                System.out.println("=======end of entity==========");
            }
        }
    }
}
