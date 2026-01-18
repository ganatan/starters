package com.ganatan.starter.continents;

import java.util.List;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/continents")
public class ContinentController {

  private final ContinentService service;

  public ContinentController(ContinentService service) {
    this.service = service;
  }

  @GetMapping
  public List<Continent> getAllContinents() {
    return service.getAll();
  }

  @GetMapping("/{id}")
  public Continent getContinentById(@PathVariable int id) {
    return service.getById(id);
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public Continent createContinent(@Valid @RequestBody ContinentRequestDTO body) {
    return service.create(body.name());
  }

  @PutMapping("/{id}")
  public Continent updateContinent(
    @PathVariable int id,
    @Valid @RequestBody ContinentRequestDTO body
  ) {
    return service.update(id, body.name());
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void deleteContinent(@PathVariable int id) {
    service.delete(id);
  }
}
