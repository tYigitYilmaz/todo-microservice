package com.qwerty.todomicroservices.userfrontservice.web;

import com.qwerty.todomicroservices.userfrontservice.domain.User;
import com.qwerty.todomicroservices.userfrontservice.secuirty.jwt.JwtInMemoryUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.security.auth.login.AccountException;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {

    private JwtInMemoryUserDetailsService userDetailService;

    @Autowired
    public UserController (JwtInMemoryUserDetailsService userDetailService){
        this.userDetailService=userDetailService;
    }

    @RequestMapping(value = "/register",method = RequestMethod.POST)
    public ResponseEntity<User> registerAccount(@RequestBody User user) throws AccountException {
        user = userDetailService.register(user);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @RequestMapping(value ="{userName}",method = RequestMethod.POST
            , consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
            , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    User userMather(@PathVariable String userName){
        return userDetailService.findUserByUsername(userName);
    }
}
