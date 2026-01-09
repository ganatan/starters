package com.ganatan.starter.continents;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/continents")
public class ContinentController {

  private final AtomicInteger idCounter = new AtomicInteger(0);
  private final List<Continent> continentList = new ArrayList<>();

  public record Continent(int id, String name) {}

  public ContinentController() {
    createContinent(new Continent(0, "Africa"));
    createContinent(new Continent(0, "America"));
    createContinent(new Continent(0, "Asia"));
    createContinent(new Continent(0, "Europe"));
    createContinent(new Continent(0, "Oceania"));
    createContinent(new Continent(0, "Antarctica"));
  }

  @GetMapping
  public List<Continent> getAllContinents() {
    return continentList;
  }

  @GetMapping("/{id}")
  public Continent getContinentById(@PathVariable int id) {
    Optional<Continent> foundContinent = findContinentById(id);

    if (foundContinent.isEmpty()) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

    return foundContinent.get();
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public Continent createContinent(@RequestBody Continent newContinent) {
    int newId = idCounter.incrementAndGet();
    Continent createdContinent = new Continent(newId, newContinent.name());
    continentList.add(createdContinent);
    return createdContinent;
  }

  @PutMapping("/{id}")
  public Continent updateContinent(@PathVariable int id, @RequestBody Continent modifiedContinent) {
    Optional<Continent> existingContinent = findContinentById(id);

    if (existingContinent.isEmpty()) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

    Continent updatedContinent = new Continent(existingContinent.get().id(), modifiedContinent.name());
    int position = continentList.indexOf(existingContinent.get());
    continentList.set(position, updatedContinent);

    return updatedContinent;
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void deleteContinent(@PathVariable int id) {
    Optional<Continent> existingContinent = findContinentById(id);

    if (existingContinent.isEmpty()) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

    continentList.remove(existingContinent.get());
  }

  private Optional<Continent> findContinentById(int id) {
    for (Continent continent : continentList) {
      if (continent.id() == id) {
        return Optional.of(continent);
      }
    }
    return Optional.empty();
  }


}