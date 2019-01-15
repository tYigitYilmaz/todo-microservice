package com.qwerty.todomicroservices.userfrontservice.secuirty.jwt;


import com.qwerty.todomicroservices.userfrontservice.domain.User;
import com.qwerty.todomicroservices.userfrontservice.domain.repository.UserDao;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.security.auth.login.AccountException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class JwtInMemoryUserDetailsService implements UserDetailsService {

  private final Logger logger = Logger.getLogger(JwtInMemoryUserDetailsService.class);

  @Autowired
  private UserDao userDao;

  @Autowired
  private PasswordEncoder passwordEncoder;



  @Override
  public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
    Optional<User> user=userDao.findByUsername(s);
    if ( user.isPresent() ) {
      return user.get();
    } else {
      throw new UsernameNotFoundException(String.format("Username[%s] not found", s));
    }
  }

  public User findUserByUsername(String username) throws UsernameNotFoundException {
    Optional<User> user = userDao.findByUsername( username );
    if ( user.isPresent() ) {
      return user.get();
    } else {
      throw new UsernameNotFoundException(String.format("Username[%s] not found", username));
    }
  }

  public User register(User user) throws AccountException {
    if ( userDao.countByUsername( user.getUsername() ) == 0 ) {
      user.setPassword(passwordEncoder.encode(user.getPassword()));
      return userDao.save( user );
    } else {
      throw new AccountException(String.format("Username[%s] already taken.", user.getUsername()));
    }
  }

  @Transactional // To successfully remove the date @Transactional annotation must be added
  public boolean removeAuthenticatedUser() throws UsernameNotFoundException {
    String username = SecurityContextHolder.getContext().getAuthentication().getName();
    User user = findUserByUsername(username);
    int del = userDao.deleteAccountById(user.getId());
    return del > 0;
  }
}


