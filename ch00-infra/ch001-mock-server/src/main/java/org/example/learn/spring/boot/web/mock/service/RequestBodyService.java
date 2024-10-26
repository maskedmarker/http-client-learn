package org.example.learn.spring.boot.web.mock.service;


import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Service
public class RequestBodyService {

    public HttpEntity<Void> foo(HttpServletRequest request, HttpServletResponse response) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("My-Header", "张三");
        HttpEntity<Void> httpEntity = new HttpEntity<>(null, headers);

        return httpEntity;
    }
}