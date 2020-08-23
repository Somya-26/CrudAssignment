package com.example.demo.controllers;

import com.example.demo.models.MyUserInfo;
import com.example.demo.services.UserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {
  @Autowired
  private PasswordEncoder passwordEncoder;
  @Autowired
  private ObjectPostProcessor<Object> objectPostProcessor;
  @Autowired
  private UserDetailsService userDetailsService;

  @PostMapping(value = "/login")
  public String userLogin(@RequestParam String userName,
                          @RequestParam String password) {

    UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(userName,
                                                                                        password);
    try {
      final AuthenticationManager authenticationManager = getAuthenticationManager();
      Authentication auth = authenticationManager.authenticate(token);
      MyUserInfo user = (MyUserInfo) (auth.getPrincipal());
      if (null == user.getUsername()) {
        return "UserName or password is not correct";
      }
    } catch (Exception ex) {
      return "UserName or password is not correct";
    }

    return "Logged In Successfully.";
  }

  private AuthenticationManager getAuthenticationManager() throws Exception {
    return new AuthenticationManagerBuilder(objectPostProcessor)
        .userDetailsService(userDetailsService).passwordEncoder(passwordEncoder)
        .and()
        .getOrBuild();
  }
}
