package com.ganatan.starter.continents;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class ContinentService {

  private final AtomicInteger idCounter = new AtomicInteger(0);
  private final List<Continent> continents = new ArrayList<>();

  public record Continent(int id, String name) {}

  public ContinentService() {
    seed("Africa", "America", "Asia", "Europe", "Oceania", "Antarctica");
  }

  public List<Continent> getAll() {
    return continents;
  }

  public Continent getById(int id) {
    return requireById(id);
  }

  public Continent create(String name) {
    int id = idCounter.incrementAndGet();
    Continent created = new Continent(id, name);
    continents.add(created);
    return created;
  }

  public Continent update(int id, String name) {
    int index = requireIndexById(id);
    Continent updated = new Continent(id, name);
    continents.set(index, updated);
    return updated;
  }

  public void delete(int id) {
    int index = requireIndexById(id);
    continents.remove(index);
  }

  private void seed(String... names) {
    for (String name : names) {
      create(name);
    }
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
