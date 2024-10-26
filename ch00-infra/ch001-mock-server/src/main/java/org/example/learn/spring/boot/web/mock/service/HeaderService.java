package org.example.learn.spring.boot.web.mock.service;


import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;

@Service
public class HeaderService {

    public HttpEntity<Resource> getPdf(String id, String type) {
        String path = "./demo.pdf";
        ClassPathResource classPathResource = new ClassPathResource(path);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
//        ContentDisposition contentDisposition = ContentDisposition.builder(type)
//                .filename(classPathResource.getFilename(), StandardCharsets.UTF_8)
//                .build();
        ContentDisposition contentDisposition = ContentDisposition.builder(type)
                .filename("中文文件名", StandardCharsets.UTF_8)
                .build();
        headers.setContentDisposition(contentDisposition);
        HttpEntity<Resource> httpEntity = new HttpEntity<>(classPathResource, headers);

        return httpEntity;
    }

    public HttpEntity<Resource> request(HttpServletRequest request, HttpServletRequest request1) {
        return null;
    }

    public HttpEntity<Resource> response(HttpServletRequest request, HttpServletRequest request1) {
        return null;
    }
}