package com.ganatan.starter.continents;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public class ContinentRepository {

  private List<Continent> continents;

  public ContinentRepository() {
    continents = new ArrayList<>();
    initData();
  }

  private void initData() {
    Continent africa = new Continent();
    africa.setId(1001);
    africa.setName("Africa");
    continents.add(africa);

    Continent northAmerica = new Continent();
    northAmerica.setId(1002);
    northAmerica.setName("North America");
    continents.add(northAmerica);
  }

  public void add(Continent continent) {
    continents.add(continent);
  }

  public List<Continent> list() {
    return continents;
  }

  public Continent get(int id) {
    for (Continent continent : continents) {
      if (continent.getId() == id) {
        return continent;
      }
    }
    return null;
  }

  public boolean update(int id, Continent newContinent) {
    for (int i = 0; i < continents.size(); i++) {
      if (continents.get(i).getId() == id) {
        newContinent.setId(id);
        continents.set(i, newContinent);
        return true;
      }
    }
    return false;
  }

  public boolean delete(int id) {
    for (int i = 0; i < continents.size(); i++) {
      if (continents.get(i).getId() == id) {
        continents.remove(i);
        return true;
      }
    }
    return false;
  }
}