package com.qwerty.microservices.userservice.domain.repository;


import com.qwerty.microservices.userservice.domain.UserAccountNumber;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserAccountDao extends CrudRepository<UserAccountNumber, Long> {
    UserAccountNumber findByUsername(String username);

    UserAccountNumber findByAccountNumber(Long accountNumber);


}
