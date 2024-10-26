package org.example.learn.spring.boot.web.mock.controller;

import org.example.learn.spring.boot.web.mock.service.ResponseBodyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/body/response")
public class ResponseBodyController {

    @Autowired
    ResponseBodyService responseBodyService;

    @RequestMapping("/empty")
    public HttpEntity<Void> empty(HttpServletRequest request, HttpServletResponse response) {
        return responseBodyService.empty(request, response);
    }
}
