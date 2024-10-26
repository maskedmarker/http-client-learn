package org.example.learn.spring.boot.web.mock.controller;

import org.example.learn.spring.boot.web.mock.service.HeaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/header")
public class HeaderController {

    @Autowired
    HeaderService headerService;

    @RequestMapping("/request")
    public HttpEntity<Resource> request(HttpServletRequest request, HttpServletResponse response) {
        return headerService.request(request, request);
    }

    @RequestMapping("/response")
    public HttpEntity<Resource> response(HttpServletRequest request, HttpServletResponse response) {
        return headerService.response(request, request);
    }
}
