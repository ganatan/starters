package com.ganatan.controllers;

import com.ganatan.models.Essai;
import com.ganatan.services.EssaiService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/essais")
public class EssaiController {

    private final EssaiService service;

    public EssaiController(EssaiService service) {
        this.service = service;
    }

    @GetMapping
    public List<Essai> getAll() {
        return service.findAll();
    }
}
