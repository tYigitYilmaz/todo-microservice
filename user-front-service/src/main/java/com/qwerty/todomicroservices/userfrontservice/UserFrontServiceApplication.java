package com.qwerty.todomicroservices.userfrontservice;

import com.qwerty.todomicroservices.userfrontservice.domain.User;
import com.qwerty.todomicroservices.userfrontservice.secuirty.jwt.JwtInMemoryUserDetailsService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import javax.security.auth.login.AccountException;
import java.util.Arrays;

@SpringBootApplication
@EnableFeignClients("com.qwerty.todomicroservices.userfrontservice")
@EnableEurekaClient
public class UserFrontServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserFrontServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner init(JwtInMemoryUserDetailsService jwtInMemoryUserDetailsService) {
        return (evt) -> Arrays.asList(

                "user,admin,username1,user2,user3".split(",")).forEach(

                username -> {
                    User user = new User();
                    user.setUsername(username);
                    if ( username.equals("user")) user.setPassword("password");
                    else user.setPassword("password");
                    user.setFirstName(username);
                    user.setLastName("LastName");
                    user.grantAuthority("ROLE_USER");
                    if ( username.equals("admin") )
                        user.grantAuthority("ROLE_ADMIN");
                    try {
                        jwtInMemoryUserDetailsService.register(user);
                    } catch (AccountException e) {
                        e.printStackTrace();
                    }
                }
        );
    }

}
