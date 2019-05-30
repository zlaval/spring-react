package com.zlrx.springreact.springreact.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/secure")
public class SecureController {

    @GetMapping
    public String secureHello() {
        return "Hello, from secured context";
    }
}
