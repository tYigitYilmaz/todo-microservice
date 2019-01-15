package com.qwerty.microservices.userservice.service.serviceImpl;


import com.qwerty.microservices.userservice.domain.UserAccountNumber;
import com.qwerty.microservices.userservice.domain.repository.UserAccountDao;
import com.qwerty.microservices.userservice.service.UserAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserAccountServiceImpl implements UserAccountService {

    private UserAccountDao userAccountDao;


    @Autowired
    public void setUserAccountDao(UserAccountDao userAccountDao) {
        this.userAccountDao = userAccountDao;
    }

    public UserAccountDao getUserAccountDao() {
        return userAccountDao;
    }

    @Override
    public void createUserAccount(String username, String firstName, String lastName) {

        UserAccountNumber userAccountNumber = new UserAccountNumber(username, firstName, lastName);

        userAccountDao.save(userAccountNumber);
    }
}
