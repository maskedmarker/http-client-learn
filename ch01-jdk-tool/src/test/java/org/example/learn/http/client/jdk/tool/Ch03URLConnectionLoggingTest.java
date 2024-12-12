package org.example.learn.http.client.jdk.tool;

import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.util.logging.ConsoleHandler;
import java.util.stream.Stream;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * -Djava.util.logging.config.file=logging.properties
 */
public class Ch03URLConnectionLoggingTest {

    @Before
    public void setup() {
        // Enable HttpURLConnection logging
        System.setProperty("java.util.logging.config.file", "logging.properties");
        System.setProperty("sun.net.www.protocol.http.HttpURLConnection.level", "ALL");
        System.setProperty("sun.net.www.protocol.http.level", "ALL");

        // Configure Logger
        Logger logger = Logger.getLogger("sun.net.www.protocol.http");
        ConsoleHandler handler = new ConsoleHandler();
        handler.setLevel(Level.ALL);
        logger.addHandler(handler);
        logger.setLevel(Level.ALL);
    }

    @Test
    public void test0() throws Exception {
        String urlString = "http://localhost:8080/myapp/users/getAllUsers";
        URL url = new URL(urlString);
        // 只是创建URLConnection对象,并不会立即建立通讯联接
        URLConnection urlConnection = url.openConnection();
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
