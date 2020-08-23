package com.example.demo.repositories;

import com.example.demo.models.ContentInfo;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContentRepository extends JpaRepository<ContentInfo, Long> {
  List<ContentInfo> findAllByUsername(String username);
}
