package com.qwerty.microservices.userservice.web;


import com.qwerty.microservices.userservice.domain.User;
import com.qwerty.microservices.userservice.service.serviceImpl.UserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.security.auth.login.AccountException;
import javax.validation.Valid;
import java.security.Principal;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class UserController {

    private UserDetailService userDetailService;

    @Autowired
    public void setUserDetailService(UserDetailService userDetailService) {
        this.userDetailService = userDetailService;
    }

    @RequestMapping(value ="/api/register",method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<User> registerAccount(@RequestBody User user) throws AccountException {
        user = userDetailService.register(user);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @PreAuthorize("isFullyAuthenticated()")
    @DeleteMapping("/api/account/remove")
    public ResponseEntity<GeneralController.RestMsg> removeAccount(Principal principal){
        boolean isDeleted = userDetailService.removeAuthenticatedUser();
        if ( isDeleted ) {
            return new ResponseEntity<>(
                    new GeneralController.RestMsg(String.format("[%s] removed.", principal.getName())),
                    HttpStatus.OK
            );
        } else {
            return new ResponseEntity<GeneralController.RestMsg>(
                    new GeneralController.RestMsg(String.format("An error ocurred while delete [%s]", principal.getName())),
                    HttpStatus.BAD_REQUEST
            );
        }
    }
    @RequestMapping(value ="{userName}",method = RequestMethod.POST
            , consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    User userMather(@PathVariable String userName){
        return userDetailService.findUserByUsername(userName);
    }
}