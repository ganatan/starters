package com.ganatan.starter.continents.inmemory;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

import com.ganatan.starter.continents.Continent;
import com.ganatan.starter.continents.ContinentRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

@Repository
@Profile("inmemory")
public class InMemoryContinentRepository implements ContinentRepository {

  private final AtomicInteger idCounter = new AtomicInteger(0);
  private final List<Continent> store = new ArrayList<>();

  public InMemoryContinentRepository() {
    seed("Africa", "America", "Asia", "Europe", "Oceania", "Antarctica");
  }

  @Override
  public List<Continent> findAll() {
    return List.copyOf(store);
  }

  @Override
  public Optional<Continent> findById(int id) {
    for (Continent c : store) {
      if (c.id() == id) {
        return Optional.of(c);
      }
    }
    return Optional.empty();
  }

  @Override
  public Continent save(String name) {
    int id = idCounter.incrementAndGet();
    Continent created = new Continent(id, name);
    store.add(created);
    return created;
  }

  @Override
  public Optional<Continent> update(int id, String name) {
    for (int i = 0; i < store.size(); i++) {
      if (store.get(i).id() == id) {
        Continent updated = new Continent(id, name);
        store.set(i, updated);
        return Optional.of(updated);
      }
    }
    return Optional.empty();
  }

  @Override
  public boolean deleteById(int id) {
    return store.removeIf(c -> c.id() == id);
  }

  private void seed(String... names) {
    for (String name : names) {
      save(name);
    }
  }
}
