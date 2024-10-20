package org.example.learn.spring.boot.web.mock.controller;

import org.example.learn.spring.boot.web.mock.model.RequestData;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/param")
public class RequestParamController {

    @RequestMapping("/multipart")
    @ResponseBody
    public String multipart(HttpServletRequest request, @ModelAttribute RequestData requestData) {
        System.out.println("request = " + request);
        return "hello";
    }

    @RequestMapping("/multipart2")
    @ResponseBody
    public String multipart2(MultipartHttpServletRequest request) {
        System.out.println("request = " + request);
        return "hello";
    }
}
