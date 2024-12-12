package org.example.learn.http.client.jdk.tool;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.util.stream.Stream;

/**
 * 想要获取url指向的资源,必须通过URLConnection来以InputStream的方式获取
 */
public class Ch01URLTest {

    @Test
    public void test0() throws IOException {
        String urlString = "http://localhost:8080/myapp/users/getAllUsers";
        URL url = new URL(urlString);
        // 并不会立即建立通讯联接
        URLConnection connection = url.openConnection();
        // 因为要获取响应的server的inputStream,肯定要先建立网络连接,然后在发送http请求,才能拿到server输出的内容
        // 会间接触发URLConnection.connect()
        // 如果是http请求,该方法在返回InputStream前,已经将stream中的报文头读取消费掉了,只剩下报文体
        // header相关的byte本来就与url对应的资源无关,仅仅是通讯中协议附加的,返回给用户的stream就应该屏蔽掉header相关的byte,仅仅包含资源本身
        InputStream inputStream = connection.getInputStream();

        // 提前知道网站的编码是utf-8
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
        Stream<String> lines = bufferedReader.lines();
        lines.forEach(System.out::println);
    }

    @Test
    public void test1() throws IOException {
        String urlString = "http://localhost:8080/myapp/users/getAllUsers";
        URL url = new URL(urlString);
        URLConnection connection = url.openConnection();
        String contentType = connection.getContentType();
        System.out.println("contentType = " + contentType);
        int contentLength = connection.getContentLength();
        System.out.println("contentLength = " + contentLength);
    }
}
