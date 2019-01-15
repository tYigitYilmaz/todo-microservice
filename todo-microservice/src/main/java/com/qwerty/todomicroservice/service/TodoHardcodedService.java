package com.qwerty.todomicroservice.service;

import com.qwerty.todomicroservice.domain.Todo;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface TodoHardcodedService {

    Todo save(Long userId,Todo todo);
    Todo findById(long id);
    Todo deleteById(long id);
    List<Todo> findAll(Long userId);
    Todo invokeUser(String userName);

}
