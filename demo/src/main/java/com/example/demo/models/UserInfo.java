package com.example.demo.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user_info")
public class UserInfo {
  @Id
  private String username;
  private String password;


  public String getPassword() {
    return this.password;
  }

  public String getUsername() {
    return this.username;
  }
}
