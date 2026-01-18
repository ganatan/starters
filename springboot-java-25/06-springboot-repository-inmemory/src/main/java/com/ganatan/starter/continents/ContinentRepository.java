package com.ganatan.starter.continents;

import java.util.List;
import java.util.Optional;

public interface ContinentRepository {
  List<Continent> findAll();
  Optional<Continent> findById(int id);
  Continent save(String name);
  Optional<Continent> update(int id, String name);
  boolean deleteById(int id);
}
