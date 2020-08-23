package com.example.demo.controllers;

import com.example.demo.models.ContentInfo;
import com.example.demo.models.MyUserInfo;
import com.example.demo.repositories.ContentRepository;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/content")
public class ContentController {

  @Autowired
  private ContentRepository contentRepository;

  @PostMapping("/save")
  public String saveContent(@AuthenticationPrincipal MyUserInfo myUserInfo,
                            @RequestParam String url) {
    String content = getDataFromUrl(url);
    if ("".equals(content)) {
      return "Connection can't be built to the url. Kindly check the url";
    }
    ContentInfo contentInfo = new ContentInfo();
    contentInfo.setUsername(myUserInfo.getUsername());
    contentInfo.setContent(content);
    contentRepository.save(contentInfo);
    return "Data saved successfully";
  }

  private String getDataFromUrl(String url) {
    try {
      URL myurl = new URL(url);
      HttpURLConnection con = (HttpURLConnection) myurl.openConnection();
      con.setRequestMethod("GET");
      String content = getConnection(con);
      if (200 == con.getResponseCode()) {
        return content;
      }
      return "";
    } catch (Exception e) {
      return "";
    }
  }

  private String getConnection(HttpURLConnection conn) {
    StringBuilder content;
    try (BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream(),
                                                                      Charset.defaultCharset()))) {
      String line;
      content = new StringBuilder();

      while ((line = in.readLine()) != null) {
        content.append(line);
        content.append(System.lineSeparator());
      }
    } catch (Exception e) {
      return "";
    } finally {
      conn.disconnect();
    }
    return content.toString();
  }

  @GetMapping("/fetch")
  public List<String> getContentForUser(@AuthenticationPrincipal MyUserInfo myUserInfo) {
    return contentRepository.findAllByUsername(myUserInfo.getUsername())
        .stream()
        .map(ContentInfo::getContent)
        .collect(Collectors.toList());

  }

}
