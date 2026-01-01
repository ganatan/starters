package com.ganatan.starter.continents;

import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class ContinentService {

  private final ContinentRepository repository;

  public ContinentService(ContinentRepository repository) {
    this.repository = repository;
  }

  public void add(Continent t) {
    repository.add(t);
  }

  public List<Continent> list() {
    return repository.list();
  }

  public Continent get(int id) {
    return repository.get(id);
  }

  public boolean update(int id, Continent t) {
    return repository.update(id, t);
  }

  public boolean delete(int id) {
    return repository.delete(id);
  }
}
