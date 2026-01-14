package com.ganatan.starter.continents;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/continents")
public class ContinentController {
  private final AtomicInteger idCounter = new AtomicInteger(0);
  private final List<Continent> continents = new ArrayList<>();

  public record Continent(int id, String name) {}

  public record ContinentRequestDTO(
    @NotBlank(message = "name is required")
    @Size(max = 50, message = "name must not exceed 50 characters")
    String name
  ) {}

  public ContinentController() {
    seed("Africa", "America", "Asia", "Europe", "Oceania", "Antarctica");
  }

  @GetMapping({ "", "/" })
  public List<Continent> getAllContinents() {
    return continents;
  }

  @GetMapping("/{id}")
  public Continent getContinentById(@PathVariable int id) {
    return requireById(id);
  }

  @PostMapping({ "", "/" })
  @ResponseStatus(HttpStatus.CREATED)
  public Continent createContinent(@Valid @RequestBody ContinentRequestDTO body) {
    return create(body.name());
  }

  @PutMapping("/{id}")
  public Continent updateContinent(@PathVariable int id, @Valid @RequestBody ContinentRequestDTO body) {
    int index = requireIndexById(id);
    Continent updated = new Continent(id, body.name());
    continents.set(index, updated);
    return updated;
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void deleteContinent(@PathVariable int id) {
    int index = requireIndexById(id);
    continents.remove(index);
  }

  private void seed(String... names) {
    for (String name : names) {
      create(name);
    }
  }

  private Continent create(String name) {
    int id = idCounter.incrementAndGet();
    Continent created = new Continent(id, name);
    continents.add(created);
    return created;
  }

  private Continent requireById(int id) {
    for (Continent c : continents) {
      if (c.id() == id) {
        return c;
      }
    }
    throw new ResponseStatusException(HttpStatus.NOT_FOUND, "CONTINENT_NOT_FOUND");
  }

  private int requireIndexById(int id) {
    for (int i = 0; i < continents.size(); i++) {
      if (continents.get(i).id() == id) {
        return i;
      }
    }
    throw new ResponseStatusException(HttpStatus.NOT_FOUND, "CONTINENT_NOT_FOUND");
  }
}