package com.qwerty.todomicroservice.domain;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TodoDao extends CrudRepository<Todo, Long> {
    List<Todo> findAllByUserId(Long userId);
}
