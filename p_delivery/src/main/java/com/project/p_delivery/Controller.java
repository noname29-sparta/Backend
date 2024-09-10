package com.project.p_delivery;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    @Value("${server.port}")
    private String port;

    @GetMapping("/hello")
    public String value() {
        return "Hello World " + port;
    }
}
