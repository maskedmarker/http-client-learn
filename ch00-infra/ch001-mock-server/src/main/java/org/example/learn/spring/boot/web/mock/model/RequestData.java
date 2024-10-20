package org.example.learn.spring.boot.web.mock.model;

import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

public class RequestData {

    private String field1;

    private String field2;

    private MultipartFile file;

    //    private Map<String, Object> field3;
    private String field3;

    public String getField1() {
        return field1;
    }

    public void setField1(String field1) {
        this.field1 = field1;
    }

    public String getField2() {
        return field2;
    }

    public void setField2(String field2) {
        this.field2 = field2;
    }

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }

    public String getField3() {
        return field3;
    }

    public void setField3(String field3) {
        this.field3 = field3;
    }
}
