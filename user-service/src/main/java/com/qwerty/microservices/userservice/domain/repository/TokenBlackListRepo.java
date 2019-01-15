package com.qwerty.microservices.userservice.domain.repository;

import com.qwerty.microservices.userservice.domain.TokenBlackList;
import org.springframework.data.repository.Repository;

import java.util.List;
import java.util.Optional;


public interface TokenBlackListRepo extends Repository<TokenBlackList, Long> {
    Optional<TokenBlackList> findByJti(String jti);
    List<TokenBlackList> queryAllByUserIdAndIsBlackListedTrue(Long userId);
    List<TokenBlackList> deleteAllByUserIdAndExpiresBefore(Long userId, Long date);
    void save(TokenBlackList tokenBlackList);

        }