package com.ganatan.starter.continents.infrastructure.persistence;

import java.util.List;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.ganatan.starter.continents.domain.Continent;
import com.ganatan.starter.continents.domain.ContinentRepository;

@Repository
@Profile("jpa")
public class JpaContinentRepository implements ContinentRepository {

  private final SpringDataContinentJpa jpa;

  public JpaContinentRepository(SpringDataContinentJpa jpa) {
    this.jpa = jpa;
  }

  @Override
  public void add(Continent continent) {
    jpa.save(toEntity(continent));
  }

  @Override
  public List<Continent> list() {
    return jpa.findAll().stream().map(this::toDomain).toList();
  }

  @Override
  public Continent get(int id) {
    return jpa.findById(id).map(this::toDomain).orElse(null);
  }

  @Override
  public boolean update(int id, Continent continent) {
    if (!jpa.existsById(id)) {
      return false;
    }
    continent.setId(id);
    jpa.save(toEntity(continent));
    return true;
  }

  @Override
  public boolean delete(int id) {
    if (!jpa.existsById(id)) {
      return false;
    }
    jpa.deleteById(id);
    return true;
  }

  private ContinentEntity toEntity(Continent continent) {
    ContinentEntity entity = new ContinentEntity();
    entity.setId(continent.getId());
    entity.setName(continent.getName());
    return entity;
  }

  private Continent toDomain(ContinentEntity entity) {
    Continent continent = new Continent();
    continent.setId(entity.getId());
    continent.setName(entity.getName());
    return continent;
  }
}
