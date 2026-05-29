package com.jbd.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("hello")
public class HelloWorldController {

    @GetMapping("/api/hello")
    public String sayHello() {
        return "Hello, World!";
    }

    @GetMapping("/api/hello-bean")
    public HelloWorldBean helloWorldBean() {
        return new HelloWorldBean("Hello, World!");
    }

    @GetMapping("/api/hello/path-variable/{name}")
    public String sayHello(@PathVariable String name) {
        return "Hello, " + name + "!";
    }

    
}
