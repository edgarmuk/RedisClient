package com.edgar.redisclient.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ViewController {

    @RequestMapping("/")
    public String home() {
        return "You have reached the home page.";
    }
}
