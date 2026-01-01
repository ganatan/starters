package com.ganatan.starter.continents.infrastructure.web;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.ganatan.starter.continents.application.ContinentService;
import com.ganatan.starter.continents.domain.Continent;

@RestController
@RequestMapping("/continents")
public class ContinentController {

  private final ContinentService service;

  public ContinentController(ContinentService service) {
    this.service = service;
  }

  @PostMapping
  public void add(@RequestBody Continent continent) {
    service.add(continent);
  }

  @GetMapping
  public List<Continent> list() {
    return service.list();
  }

  @GetMapping("/{id}")
  public Continent get(@PathVariable int id) {
    return service.get(id);
  }

  @PutMapping("/{id}")
  public boolean update(@PathVariable int id, @RequestBody Continent continent) {
    return service.update(id, continent);
  }

  @DeleteMapping("/{id}")
  public boolean delete(@PathVariable int id) {
    return service.delete(id);
  }
}
