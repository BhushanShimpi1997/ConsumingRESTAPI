package com.consume.controller;

import com.consume.model.Contact;
import com.consume.model.Response;
import com.consume.proxy.ContactProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
public class ContactRestController {

    @Autowired
    private ContactProxy contactProxy;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private WebClient webClient;

    @GetMapping("/getMessages")
    public List<Contact> getMessagesByStatus(@RequestParam("status")String status){
        return contactProxy.getMessagesByStatus(status);
    }

    @PostMapping("/saveMsg")
    public ResponseEntity<Response> saveMsg(@RequestBody Contact contact){
        String url="http://localhost:8080/api/contact/saveMsg";
        HttpHeaders headers=new HttpHeaders();
        headers.add("invocationFrom","RestTemplate");
        HttpEntity<Contact> requestEntity=new HttpEntity<>(contact,headers);
        ResponseEntity<Response> responseEntity = restTemplate.exchange(url, HttpMethod.POST,
                requestEntity, Response.class);
        return responseEntity;
    }

    @PostMapping("/saveMessage")
    public Mono<Response> saveMessages(@RequestBody Contact contact){
        String uri="http://localhost:8080/api/contact/saveMsg";
        return webClient.post().uri(uri)
                .header("invocationFrom","WebClient")
                .body(Mono.just(contact), Contact.class)
                .retrieve()
                .bodyToMono(Response.class);
    }
}
