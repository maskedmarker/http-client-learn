package org.example.learn.spring.boot.web.mock.model;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;

public class RequestData {

    private String key1;

    private String key2;

    private MultipartFile file;

    public String getKey1() {
        return key1;
    }

    public void setKey1(String key1) {
        this.key1 = key1;
    }

    public String getKey2() {
        return key2;
    }

    public void setKey2(String key2) {
        this.key2 = key2;
    }

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }
}
