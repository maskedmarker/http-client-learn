package com.example.learn.http.httpbin;


import org.gaul.httpbin.HttpBin;

import java.net.URI;

public class HttpBinServerBootstrap {

    public static void main(String[] args) throws Exception {
        URI httpBinEndpoint = URI.create("http://127.0.0.1:8080");
        HttpBin httpBin = new HttpBin(httpBinEndpoint);
        httpBin.start();
    }
}
