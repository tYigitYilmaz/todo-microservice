package com.qwerty.todomicroservice.web;


import com.qwerty.todomicroservice.domain.Todo;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "netflix-zuul-gateway")
@RibbonClient(name = "user-service")
@Component
public interface UserProxy {

    @RequestMapping(value ="/user-service/{username}",method = RequestMethod.POST
            , consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
            , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    Todo invokeUser(@PathVariable String username);
}
