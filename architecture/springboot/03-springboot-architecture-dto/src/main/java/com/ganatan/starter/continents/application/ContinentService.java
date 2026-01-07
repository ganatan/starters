package com.ganatan.starter.continents.application;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ganatan.starter.continents.domain.Continent;
import com.ganatan.starter.continents.domain.ContinentRepository;

@Service
public class ContinentService {

  private final ContinentRepository repo;

  public ContinentService(ContinentRepository repo) {
    this.repo = repo;
  }

  public void add(Continent continent) {
    repo.add(continent);
  }

  public List<Continent> list() {
    return repo.list();
  }

  public Continent get(int id) {
    return repo.get(id);
  }

  public boolean update(int id, Continent continent) {
    return repo.update(id, continent);
  }

  public boolean delete(int id) {
    return repo.delete(id);
  }
}
