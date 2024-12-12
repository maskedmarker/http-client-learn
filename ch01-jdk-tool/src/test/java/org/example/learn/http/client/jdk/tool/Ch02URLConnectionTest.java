package org.example.learn.http.client.jdk.tool;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.util.stream.Stream;

public class Ch02URLConnectionTest {

    @Test
    public void test0() throws Exception {
        String urlString = "http://localhost:8080/myapp/users/getAllUsers";
        URL url = new URL(urlString);
        // 只是创建URLConnection对象,并不会立即建立通讯联接
        URLConnection urlConnection = url.openConnection();

        urlConnection.setConnectTimeout(2000);
        urlConnection.setReadTimeout(20000);
        urlConnection.setDoInput(true);
        urlConnection.setDoOutput(true);
        urlConnection.setAllowUserInteraction(false); // 比如要用户输入密码,操作系统会提示
        urlConnection.addRequestProperty("Accept", "application/xml");

        // connect()方法调用返回时,已经创建了与server的tcp连接,此时并没有将http协议的request发送给server
        urlConnection.connect();

        // 获取响应
        InputStream inputStream = urlConnection.getInputStream();
        // 提前知道网站的编码是utf-8
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
        Stream<String> lines = bufferedReader.lines();
        lines.forEach(System.out::println);
        inputStream.close();
    }
}
