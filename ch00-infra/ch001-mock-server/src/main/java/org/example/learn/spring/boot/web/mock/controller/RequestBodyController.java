package org.example.learn.spring.boot.web.mock.controller;

import org.example.learn.spring.boot.web.mock.service.RequestBodyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/body/request")
public class RequestBodyController {

    @Autowired
    RequestBodyService requestBodyService;

    @RequestMapping("/foo")
    public void foo(HttpServletRequest request, HttpServletResponse response) {

    }
}
