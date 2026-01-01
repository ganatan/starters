package com.ganatan.starter.continents;

import java.util.List;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/continents")
public class ContinentController {

  private final ContinentService service;

  public ContinentController(ContinentService service) {
    this.service = service;
  }

  @PostMapping
  public void add(@RequestBody Continent t) {
    service.add(t);
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
  public boolean update(@PathVariable int id, @RequestBody Continent t) {
    return service.update(id, t);
  }

  @DeleteMapping("/{id}")
  public boolean delete(@PathVariable int id) {
    return service.delete(id);
  }
}
