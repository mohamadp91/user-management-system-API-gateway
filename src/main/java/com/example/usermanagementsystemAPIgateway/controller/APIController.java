package com.example.usermanagementsystemAPIgateway.controller;


import com.example.usermanagementsystemAPIgateway.model.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.http.client.reactive.ClientHttpConnector;
import org.springframework.http.codec.ClientCodecConfigurer;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.ExchangeFunction;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriBuilderFactory;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;
import java.util.function.Consumer;


@RestController
public class APIController {


    HttpHeaders headers = new HttpHeaders();

    @Value("${user-management-system.url}")
    private String url;

    @Autowired
    WebClient webClient;

    @CrossOrigin
    @PostMapping(value = "/users")
    public Mono<UserModel> addUser(@RequestBody UserModel user) {

        return webClient
                .post()
                .uri(url + "/users")
                .header(HttpHeaders.CONTENT_TYPE,MediaType.APPLICATION_JSON_VALUE)
                .body(Mono.just(user),UserModel.class)
                .retrieve()
                .bodyToMono(UserModel.class);
    }

    @CrossOrigin
    @GetMapping("/users/{id}")
    public Mono<UserModel> getUser(@PathVariable long id) {

        return webClient
                .get()
                .uri(url + "/users/" + id)
                .header(HttpHeaders.CONTENT_TYPE,MediaType.APPLICATION_JSON_VALUE)
                .retrieve()
                .bodyToMono(UserModel.class);
    }

    @CrossOrigin
    @GetMapping("/users")
    public Flux<UserModel> getUsers() {

        return webClient
                .get()
                .uri(url +"/users")
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .retrieve()
                .bodyToFlux(UserModel.class);
    }

    @CrossOrigin
    @DeleteMapping("/users/{id}")
    public Mono<UserModel> deleteUser(@PathVariable long id) {

        return  webClient
                .delete()
                .uri(url + "/users/" + id)
                .header(HttpHeaders.CONTENT_TYPE,MediaType.APPLICATION_JSON_VALUE)
                .retrieve()
                .bodyToMono(UserModel.class);
    }

}
