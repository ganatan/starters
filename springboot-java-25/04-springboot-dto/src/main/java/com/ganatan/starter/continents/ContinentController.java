package com.ganatan.starter.continents;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

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

  public record Continent(int id, String name) {}

  public record CreateContinentRequestDTO(
    @NotBlank
    @Size(max = 50)
    String name
  ) {}

  public record UpdateContinentRequestDTO(
    @NotBlank
    @Size(max = 50)
    String name
  ) {}

  public ContinentController() {
    seed("Africa", "America", "Asia", "Europe", "Oceania", "Antarctica");
  }

  @GetMapping
  public List<Continent> getAllContinents() {
    return continentList;
  }

  @GetMapping("/{id}")
  public Continent getContinentById(@PathVariable int id) {
    return findRequiredById(id);
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public Continent createContinent(@Valid @RequestBody CreateContinentRequestDTO dto) {
    Continent created = new Continent(nextId(), normalize(dto.name()));
    continentList.add(created);
    return created;
  }

  @PutMapping("/{id}")
  public Continent updateContinent(@PathVariable int id, @Valid @RequestBody UpdateContinentRequestDTO dto) {
    int index = findIndexById(id);
    if (index == -1) {
      throw notFound();
    }
    Continent updated = new Continent(id, normalize(dto.name()));
    continentList.set(index, updated);
    return updated;
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void deleteContinent(@PathVariable int id) {
    int index = findIndexById(id);
    if (index == -1) {
      throw notFound();
    }
    continentList.remove(index);
  }

  private void seed(String... names) {
    for (String name : names) {
      Continent created = new Continent(nextId(), normalize(name));
      continentList.add(created);
    }
  }

  private int nextId() {
    return idCounter.incrementAndGet();
  }

  private static String normalize(String name) {
    return name.trim();
  }

  private Continent findRequiredById(int id) {
    if (id <= 0) {
      throw notFound();
    }
    Optional<Continent> found = continentList.stream()
      .filter(c -> c.id() == id)
      .findFirst();
    return found.orElseThrow(this::notFound);
  }

  private int findIndexById(int id) {
    if (id <= 0) {
      return -1;
    }
    for (int i = 0; i < continentList.size(); i++) {
      if (continentList.get(i).id() == id) {
        return i;
      }
    }
    return -1;
  }

  private ResponseStatusException notFound() {
    return new ResponseStatusException(HttpStatus.NOT_FOUND);
  }
}
