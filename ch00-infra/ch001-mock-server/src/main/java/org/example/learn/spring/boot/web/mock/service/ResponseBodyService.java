package org.example.learn.spring.boot.web.mock.service;


import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

/**
 * percent-encoding (also known as URL encoding) typically uses UTF-8 as the default character encoding for encoding non-ASCII characters.
 */
@Service
public class ResponseBodyService {

    /**
     * response的header不限制,body必须是空的
     */
    public HttpEntity<Void> empty(HttpServletRequest request, HttpServletResponse response) {
        HttpHeaders headers = new HttpHeaders();
//        headers.add("My-Header", "张三");
        try {
            headers.add("My-Header", URLEncoder.encode("张三", StandardCharsets.UTF_8.name()));
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
        HttpEntity<Void> httpEntity = new HttpEntity<>(null, headers);

        return httpEntity;
    }
}