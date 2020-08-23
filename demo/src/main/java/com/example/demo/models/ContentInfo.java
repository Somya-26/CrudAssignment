package com.example.demo.models;

import javax.persistence.*;

@Entity
@Table(name = "content_info")
public class ContentInfo {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;
  private String username;
  private String content;

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public void setUsername(String username) {
    this.username = username;
  }
}
