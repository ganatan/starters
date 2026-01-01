package com.ganatan.starter.continents.domain;

import java.util.List;

public interface ContinentRepository {
  void add(Continent continent);
  List<Continent> list();
  Continent get(int id);
  boolean update(int id, Continent continent);
  boolean delete(int id);
}
