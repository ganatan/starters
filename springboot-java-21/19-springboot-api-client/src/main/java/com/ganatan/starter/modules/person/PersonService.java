package com.ganatan.starter.modules.person;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Service
public class PersonService {

    private final WebClient webClient;

    public PersonService(WebClient.Builder builder) {
        this.webClient = builder.baseUrl("https://jsonplaceholder.typicode.com").build();
    }

    public List<Person> fetchPersons() {
        return webClient.get()
                .uri("/users")
                .retrieve()
                .bodyToFlux(Person.class)
                .collectList()
                .block();
    }
}
