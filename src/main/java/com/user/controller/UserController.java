package com.user.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.user.dto.UserRequest;
import com.user.dto.UserResponse;
import com.user.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {
  private final UserService userService;

  @PostMapping
  public List<UserResponse> create(@RequestBody List<UserRequest> userRequests ){
    return userService.create( userRequests );
  }

  @GetMapping
  public List<UserResponse> getAll(){
    return userService.getAll();
  }

  @GetMapping("{id}")
  public UserResponse getById(@PathVariable UUID id ){
    return userService.getById(id);
  }

  @GetMapping("download/csv")
  public ResponseEntity<String> downloadUser(){
    String filename = "users.csv";
    ContentDisposition contentDisposition = ContentDisposition.builder("attachment")
        .filename(filename)
        .build();

    HttpHeaders headers = new HttpHeaders();
    headers.setContentDisposition(contentDisposition);

    return ResponseEntity.ok()
        .headers(headers)
        .contentType( MediaType.parseMediaType("text/csv"))
        .body(userService.downloadCsvForAllUsers());
  }
}
