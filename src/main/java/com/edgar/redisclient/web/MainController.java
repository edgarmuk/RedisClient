package com.edgar.redisclient.web;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value="/users")
public class MainController {

    @RequestMapping(value="{user}", method = RequestMethod.GET)
    public String user(@PathVariable String user) {
        return "You have reached the user page for " + user + ".";
    }
}
