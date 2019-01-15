package com.qwerty.todomicroservice.web;


import com.qwerty.todomicroservice.helloWorld.HelloWorldBean;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class HelloWorldController {


    @RequestMapping(method = RequestMethod.GET,path = "/hello-world")
    public String helloWorld(){
        return "Hello World!";
    }
    @RequestMapping(method = RequestMethod.GET,path = "/hello-world-bean")
    public HelloWorldBean helloWorldBean(){
        throw new RuntimeException("some Error has Occurred");
        //return new HelloWorldBean("Hello World");
    }
    @RequestMapping(method = RequestMethod.GET,path = "/hello-world/path-variable/{name}")
    public HelloWorldBean helloWorldBean(@PathVariable String name){
        return new HelloWorldBean(String.format("Hello World, %s",name));
    }
}
