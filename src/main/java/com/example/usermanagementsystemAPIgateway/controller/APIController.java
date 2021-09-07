package com.example.usermanagementsystemAPIgateway.controller;


import com.example.usermanagementsystemAPIgateway.model.UserModel;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
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

    @Value("${user-management-system.name}")
    private String url;

    @Value("SERVER_IS_DISCONNECT")
    private String SERVER_IS_DISCONNECT;

    @Qualifier("getWebClient")
    @Autowired
    WebClient.Builder webClient;


    @CrossOrigin
    @GetMapping(value = "/check_connection")
    @CircuitBreaker(name = "main" , fallbackMethod = "checkFallback")
    public String checkConnection() {
        return webClient
                .build()
                .get()
                .uri(url + "/check_connection")
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }

    public String checkFallback(Throwable t){
        return SERVER_IS_DISCONNECT;
    }

    @CrossOrigin
    @PostMapping(value = "/users")
    @CircuitBreaker(name = "main", fallbackMethod = "addUserFallBack")
    public UserModel addUser(@RequestBody UserModel user) {

        return webClient
                .build()
                .post()
                .uri(url + "/users")
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .body(Mono.just(user), UserModel.class)
                .retrieve()
                .bodyToMono(UserModel.class)
                .block();
    }

    public UserModel addUserFallBack(Throwable t) {
        return new UserModel();
    }


    @CrossOrigin
    @GetMapping("/users/{id}")
    @CircuitBreaker(name = "main", fallbackMethod = "getUserFallback")
    public UserModel getUser(@PathVariable long id) {

        return webClient
                .build()
                .get()
                .uri(url + "/users/" + id)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .retrieve()
                .bodyToMono(UserModel.class)
                .block();
    }

    public UserModel getUserFallback(Throwable t) {
        return new UserModel();
    }


    @CrossOrigin
    @GetMapping("/users")
    @CircuitBreaker(name = "main", fallbackMethod = "fallback")
    public Flux<UserModel> getUsers() {

        return webClient
                .build()
                .get()
                .uri(url + "/users")
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .retrieve()
                .bodyToFlux(UserModel.class);
    }

    public Flux<UserModel> fallback(Throwable t){
        return Flux.just(new UserModel());
    }

    @CrossOrigin
    @DeleteMapping("/users/{id}")
    @CircuitBreaker(name = "main", fallbackMethod = "deleteUserFallback")
    public UserModel deleteUser(@PathVariable long id) {

        return webClient
                .build()
                .delete()
                .uri(url + "/users/" + id)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .retrieve()
                .bodyToMono(UserModel.class)
                .block();
    }

    public UserModel deleteUserFallback(Throwable t) {
        return new UserModel();
    }

}
