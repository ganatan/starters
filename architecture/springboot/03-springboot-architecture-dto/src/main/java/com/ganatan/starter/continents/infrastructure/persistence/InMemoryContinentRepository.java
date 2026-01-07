package com.ganatan.starter.continents.infrastructure.persistence;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.ganatan.starter.continents.domain.Continent;
import com.ganatan.starter.continents.domain.ContinentRepository;

@Repository
@Profile("inmemory")
public class InMemoryContinentRepository implements ContinentRepository {

  private final List<Continent> continents = new ArrayList<>();

  public InMemoryContinentRepository() {
    System.out.println("0000000000002");
    Continent a = new Continent();
    a.setId(1001);
    a.setName("Africa");
    continents.add(a);

    Continent b = new Continent();
    b.setId(1002);
    b.setName("North America");
    continents.add(b);
  }

  @Override
  public void add(Continent continent) {
    continents.add(continent);
  }

  @Override
  public List<Continent> list() {
    System.out.println("0000000000003");
    return continents;
  }

  @Override
  public Continent get(int id) {
    for (Continent c : continents) {
      if (c.getId() == id) return c;
    }
    return null;
  }

  @Override
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

  @Override
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



//package com.ganatan.starter.continents.infrastructure.persistence;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import org.springframework.stereotype.Repository;
//
//import com.ganatan.starter.continents.domain.Continent;
//import com.ganatan.starter.continents.domain.ContinentRepository;
//
//@Repository
//public class InMemoryContinentRepository implements ContinentRepository {
//
//  private final List<Continent> continents;
//
//  public InMemoryContinentRepository() {
//    continents = new ArrayList<>();
//    seed();
//  }
//
//  private void seed() {
//    Continent a = new Continent();
//    a.setId(1001);
//    a.setName("Africa");
//    continents.add(a);
//
//    Continent b = new Continent();
//    b.setId(1002);
//    b.setName("North America");
//    continents.add(b);
//  }
//
//  @Override
//  public void add(Continent continent) {
//    continents.add(continent);
//  }
//
//  @Override
//  public List<Continent> list() {
//    return continents;
//  }
//
//  @Override
//  public Continent get(int id) {
//    for (Continent c : continents) {
//      if (c.getId() == id) return c;
//    }
//    return null;
//  }
//
//  @Override
//  public boolean update(int id, Continent continent) {
//    for (int i = 0; i < continents.size(); i++) {
//      if (continents.get(i).getId() == id) {
//        continent.setId(id);
//        continents.set(i, continent);
//        return true;
//      }
//    }
//    return false;
//  }
//
//  @Override
//  public boolean delete(int id) {
//    for (int i = 0; i < continents.size(); i++) {
//      if (continents.get(i).getId() == id) {
//        continents.remove(i);
//        return true;
//      }
//    }
//    return false;
//  }
//}
