package com.ganatan.starter.continents;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/continents")
public class ContinentController {

  private final AtomicInteger idCounter = new AtomicInteger(0);
  private final List<Continent> continentList = new ArrayList<>();

  public record Continent(int id, String name) {
  }

  public ContinentController() {
    init("Africa", "America", "Asia", "Europe", "Oceania", "Antarctica");
  }

  @GetMapping({"", "/"})
  public List<Continent> getAllContinents() {
    return continentList;
  }

  @GetMapping("/{id}")
  public Continent getContinentById(@PathVariable int id) {
    return requireById(id);
  }

  @PostMapping({"", "/"})
  @ResponseStatus(HttpStatus.CREATED)
  public Continent createContinent(@RequestBody Continent body) {
    return create(body.name());
  }

  @PutMapping("/{id}")
  public Continent updateContinent(@PathVariable int id, @RequestBody Continent body) {
    Continent existing = requireById(id);
    int index = continentList.indexOf(existing);
    Continent updated = new Continent(id, body.name());
    continentList.set(index, updated);
    return updated;
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void deleteContinent(@PathVariable int id) {
    Continent existing = requireById(id);
    continentList.remove(existing);
  }

  private void init(String... names) {
    for (String name : names) {
      create(name);
    }
  }

  private Continent create(String name) {
    int id = idCounter.incrementAndGet();
    Continent created = new Continent(id, name);
    continentList.add(created);
    return created;
  }

  private Continent requireById(int id) {
    return findById(id).orElseThrow(() ->
      new ResponseStatusException(HttpStatus.NOT_FOUND, "CONTINENT_NOT_FOUND"));
  }

  private Optional<Continent> findById(int id) {
    for (Continent c : continentList) {
      if (c.id() == id) {
        return Optional.of(c);
      }
    }
    return Optional.empty();
  }
}
