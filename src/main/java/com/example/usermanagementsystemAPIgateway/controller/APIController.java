package com.example.usermanagementsystemAPIgateway.controller;


import com.example.usermanagementsystemAPIgateway.model.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

@RestController
public class APIController {

    @Autowired
    private RestTemplate restTemplate;

    HttpHeaders headers = new HttpHeaders();

    @Value("${user-management-system.url}")
    private String url;

    @CrossOrigin
    @PostMapping(value = "/users")
    public ResponseEntity<UserModel> addUser(@RequestBody UserModel user) {

        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        HttpEntity<UserModel> entity = new HttpEntity<UserModel>(user,headers);

        return restTemplate.exchange(
                url + "/users",
                HttpMethod.POST,
                entity,
                UserModel.class);

    }

    @CrossOrigin
    @GetMapping("/users/{id}")
    public ResponseEntity<UserModel> getUser(@PathVariable long id) {

        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        HttpEntity<UserModel> entity = new HttpEntity<UserModel>(headers);

        return restTemplate.exchange(
                url + "/users/" + id,
                HttpMethod.GET,
                entity,
                UserModel.class);

    }

    @CrossOrigin
    @GetMapping("/users")
    public ResponseEntity<String> getUsers() {

        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        HttpEntity<UserModel> entity = new HttpEntity<UserModel>(headers);

        return restTemplate.exchange(
                url + "/users",
                HttpMethod.GET,
                entity,
                String.class);
    }

    @CrossOrigin
    @DeleteMapping("/users/{id}")
    public ResponseEntity<UserModel> deleteUser(@PathVariable long id) {

        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        HttpEntity<UserModel> entity = new HttpEntity<UserModel>(headers);

        return restTemplate.exchange(
                url + "/users/" + id,
                HttpMethod.DELETE,
                entity,
                UserModel.class);
    }

}
