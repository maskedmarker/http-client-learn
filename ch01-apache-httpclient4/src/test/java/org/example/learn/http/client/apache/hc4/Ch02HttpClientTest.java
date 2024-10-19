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
import org.example.learn.http.client.apache.hc4.util.HttpClientUtils;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 */
public class Ch02HttpClientTest {

    @Before
    public void setup() {
        HttpClientUtils.initLog();
    }

    @Test
    public void test01() throws IOException {
        try (CloseableHttpClient httpclient = HttpClients.createDefault()) {
            HttpGet httpGet = new HttpGet("http://localhost:8080/myapp/users/getAllUsers");

            try (CloseableHttpResponse response = httpclient.execute(httpGet)) {
                System.out.println(response.getStatusLine());
                HttpEntity entity = response.getEntity();
                // 虽然返回的是json字符串,但是entity并不是StringEntity.StringEntity通常用于应用代码发送request时.
                System.out.println("entity.getClass() = " + entity.getClass());

                // 如果无法从Content-Type header中获取到编码信息,就用http的默认编码ISO_8859_1
                String responseBody = EntityUtils.toString(entity);
                System.out.println("responseBody = " + responseBody);
            }
        }
    }

}
