package com.ganatan.starter.controllers;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/essais")
public class EssaiController {

    private final EssaiRepository repository;

    public EssaiController(EssaiRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<Essai> getAll() {
        return repository.findAll();
    }
}
