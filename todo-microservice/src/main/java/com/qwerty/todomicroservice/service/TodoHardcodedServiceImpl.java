package com.qwerty.todomicroservice.service;

import com.qwerty.todomicroservice.domain.Todo;
import com.qwerty.todomicroservice.domain.TodoDao;
import com.qwerty.todomicroservice.web.UserProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TodoHardcodedServiceImpl implements TodoHardcodedService {

    @Autowired
    private UserProxy userProxy;

    private TodoDao todoDao;

    @Autowired
    public TodoHardcodedServiceImpl(TodoDao todoDao){
            this.todoDao=todoDao;

    }

    private static List<Todo> todos = new ArrayList<>();
    private static long idCounter = 0;

    @Override
    public List<Todo>findAll(Long userId){

        return todoDao.findAllByUserId(userId);
    }

    @Override
    public Todo save(Long userId,Todo todo){
        if(todo.getId()==-1){
            todo.setId(++idCounter);
            todo.setUserId(userId);
            todoDao.save(todo);
        }else{
            deleteById(todo.getId());
            todoDao.save(todo);
        }
        return todo;
    }
    @Override
    public Todo deleteById(long id){
        Todo todo = findById(id);

        if(todo==null)
            return null;
        else{
            todoDao.delete(todo);
        }return null;
    }

    @Override
    public Todo findById(long id) {
        for(Todo todo:todos){
            if(todoDao.findById(id).isPresent()){
                return todo;
            }
        }return null;
    }

    @Override
    public Todo invokeUser(String userName){
        return userProxy.invokeUser(userName);
    }
}
