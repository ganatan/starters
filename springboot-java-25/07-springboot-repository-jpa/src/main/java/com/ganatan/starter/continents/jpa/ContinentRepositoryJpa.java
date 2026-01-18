package com.ganatan.starter.continents.jpa;

import com.ganatan.starter.continents.*;
import java.util.List;
import java.util.Optional;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

@Repository
@Profile("jpa")
public class ContinentRepositoryJpa implements ContinentRepository {

  private final ContinentSpringDataRepository jpa;

  public ContinentRepositoryJpa(ContinentSpringDataRepository jpa) {
    this.jpa = jpa;
  }

  @Override
  public List<Continent> findAll() {
    return jpa.findAll().stream().map(this::toDomain).toList();
  }

  @Override
  public Optional<Continent> findById(int id) {
    return jpa.findById(id).map(this::toDomain);
  }

  @Override
  public Continent save(String name) {
    ContinentEntity entity = new ContinentEntity();
    entity.setName(name);
    return toDomain(jpa.save(entity));
  }

  @Override
  public Optional<Continent> update(int id, String name) {
    Optional<ContinentEntity> existing = jpa.findById(id);
    if (existing.isEmpty()) {
      return Optional.empty();
    }
    ContinentEntity entity = existing.get();
    entity.setName(name);
    return Optional.of(toDomain(jpa.save(entity)));
  }

  @Override
  public boolean deleteById(int id) {
    if (!jpa.existsById(id)) {
      return false;
    }
    jpa.deleteById(id);
    return true;
  }

  private Continent toDomain(ContinentEntity entity) {
    return new Continent(entity.getId(), entity.getName());
  }
}
