package com.example.usermanagementsystemAPIgateway.controller;


import com.example.usermanagementsystemAPIgateway.model.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@RestController
public class APIController {

    @Value("http://USER-MANAGEMENT-SERVICE")
    private String url;

    @Qualifier("getWebClient")
    @Autowired
    WebClient.Builder webClient;

    @CrossOrigin
    @PostMapping(value = "/users")
    public UserModel addUser(@RequestBody UserModel user) {

        return webClient
                .build()
                .post()
                .uri(url + "/users")
                .header(HttpHeaders.CONTENT_TYPE,MediaType.APPLICATION_JSON_VALUE)
                .body(Mono.just(user),UserModel.class)
                .retrieve()
                .bodyToMono(UserModel.class)
                .block();
    }

    @CrossOrigin
    @GetMapping("/users/{id}")
    public UserModel getUser(@PathVariable long id) {

        return webClient
                .build()
                .get()
                .uri(url + "/users/" + id)
                .header(HttpHeaders.CONTENT_TYPE,MediaType.APPLICATION_JSON_VALUE)
                .retrieve()
                .bodyToMono(UserModel.class)
                .block();
    }

    @CrossOrigin
    @GetMapping("/users")
    public Flux<UserModel> getUsers() {

        return webClient
                .build()
                .get()
                .uri(url +"/users")
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .retrieve()
                .bodyToFlux(UserModel.class);
    }

    @CrossOrigin
    @DeleteMapping("/users/{id}")
    public UserModel deleteUser(@PathVariable long id) {

        return  webClient
                .build()
                .delete()
                .uri(url + "/users/" + id)
                .header(HttpHeaders.CONTENT_TYPE,MediaType.APPLICATION_JSON_VALUE)
                .retrieve()
                .bodyToMono(UserModel.class)
                .block();
    }

}
