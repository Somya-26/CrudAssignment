package com.example.demo.repositories;

import com.example.demo.models.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserInfo, String> {

  UserInfo findByUsernameAndPassword(String username, String password);

  UserInfo findByUsername(String name);
}
