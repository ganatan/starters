package com.ganatan.starter.continents.jpa;

import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

@Profile("jpa")
public interface ContinentSpringDataRepository
  extends JpaRepository<ContinentEntity, Integer> {}
