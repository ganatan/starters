package com.ganatan.starter.continents;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class ContinentService {

  private final ContinentRepository repository;

  public ContinentService(ContinentRepository repository) {
    this.repository = repository;
  }

  public List<Continent> getAll() {
    return repository.findAll();
  }

  public Continent getById(int id) {
    return repository.findById(id)
      .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "CONTINENT_NOT_FOUND"));
  }

  public Continent create(String name) {
    return repository.save(name);
  }

  public Continent update(int id, String name) {
    return repository.update(id, name)
      .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "CONTINENT_NOT_FOUND"));
  }

  public void delete(int id) {
    if (!repository.deleteById(id)) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "CONTINENT_NOT_FOUND");
    }
  }
}
