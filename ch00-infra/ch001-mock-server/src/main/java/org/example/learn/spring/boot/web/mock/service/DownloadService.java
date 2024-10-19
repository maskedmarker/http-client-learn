package org.example.learn.spring.boot.web.mock.service;


import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;

/**
 * spring的HttpEntity包含了header和body,而apache http-client的HttpEntity指的是body
 * HttpEntity(Represents an HTTP request or response entity, consisting of headers and body.)
 */
@Service
public class DownloadService {

    public HttpEntity<Resource> getPdf(String id, String type) {
        String path = "./demo.pdf";
        ClassPathResource classPathResource = new ClassPathResource(path);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        ContentDisposition contentDisposition = ContentDisposition.builder(type)
                .filename(classPathResource.getFilename(), StandardCharsets.UTF_8)
                .build();
        headers.setContentDisposition(contentDisposition);
        HttpEntity<Resource> httpEntity = new HttpEntity<>(classPathResource, headers);

        return httpEntity;
    }

}