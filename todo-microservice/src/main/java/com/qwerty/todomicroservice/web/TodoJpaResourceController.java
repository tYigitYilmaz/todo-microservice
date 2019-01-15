package com.qwerty.todomicroservice.web;


import com.qwerty.todomicroservice.domain.Todo;
import com.qwerty.todomicroservice.service.TodoHardcodedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class TodoJpaResourceController {
    private TodoHardcodedService todoHardcodedService;

    @Autowired
    public TodoJpaResourceController(TodoHardcodedService todoHardcodedService){
        this.todoHardcodedService = todoHardcodedService;
    }

    @RequestMapping(value = "jpa/users/{username}/todos",method = RequestMethod.GET)
    public List<Todo> getAllTodos(@PathVariable String username){
        Todo userIdCatcher = todoHardcodedService.invokeUser(username);
        return todoHardcodedService.findAll(userIdCatcher.getUserId());
    }

    @RequestMapping(value = "jpa/users/{username}/todos/{id}",method = RequestMethod.GET)
    public Todo getTodo(@PathVariable String username,@PathVariable long id){
        return todoHardcodedService.findById(id);
    }


    @RequestMapping(value = "jpa/users/{username}/todos/{id}",method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteTodo(
            @PathVariable String username,@PathVariable long id){
        Todo todo = todoHardcodedService.deleteById(id);
        if(todo!=null){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @RequestMapping(value = "jpa/users/{username}/todos/{id}",method = RequestMethod.PUT)
    public ResponseEntity<Todo> updateTodo(
            @PathVariable String username,
            @PathVariable long id,
            @RequestBody Todo todo) {
        Todo userIdCatcher = todoHardcodedService.invokeUser(username);
        Todo todoUpdated = todoHardcodedService.save(userIdCatcher.getUserId(),todo);
        return new ResponseEntity<Todo>(todo, HttpStatus.OK);
    }

    @RequestMapping(value = "jpa/users/{username}/todos",method = RequestMethod.POST)
    public ResponseEntity<Void> CreateTodo(
            @PathVariable String username,
            @RequestBody Todo todo) {

        Todo userIdCatcher = todoHardcodedService.invokeUser(username);
        Todo createdTodo = todoHardcodedService.save(userIdCatcher.getUserId(),todo);
        URI uri =  ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(createdTodo.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }
}