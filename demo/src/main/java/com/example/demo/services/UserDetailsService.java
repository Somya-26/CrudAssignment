package com.example.demo.services;

import com.example.demo.models.MyUserInfo;
import com.example.demo.models.UserInfo;
import com.example.demo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsService
    implements org.springframework.security.core.userdetails.UserDetailsService {

  @Autowired
  private UserRepository userRepository;

  @Override
  public UserDetails loadUserByUsername(String username) {
    UserInfo user = userRepository.findByUsername(username);
    if (null == user) {
      System.out.println("Error: Username not found");
      throw new UsernameNotFoundException(username);
    }
    return new MyUserInfo(user);
  }
}
