package com.example.shirospringboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageController {
    @RequestMapping("index.html")
    public String indexHtml() {
        return "index";
    }

    @RequestMapping("register.html")
    public String registerHtml() {
        return "register";
    }

    @RequestMapping("login.html")
    public String loginHtml() {
        return "login";
    }

    @RequestMapping("/")
    public String loginRoot() {
        return "login.html";
    }
}
