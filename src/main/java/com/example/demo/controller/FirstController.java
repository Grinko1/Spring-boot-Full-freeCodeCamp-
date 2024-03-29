package com.example.demo.controller;

import org.springframework.web.bind.annotation.*;

@RestController

public class FirstController {

    @GetMapping("/hello")
    public String hello(){
        return  "Hello from first controller";
    }
    @PostMapping("/post")
    public String post(@RequestBody String message){
        return "Created: " + message;
    }
    @GetMapping("/welcome")
    public String params(@RequestParam("name") String name,@RequestParam("age") String age ){
        return  "Request params name:- " + name +", age:- " + age
                ;
    }
}
