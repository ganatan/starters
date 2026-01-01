package com.ganatan.starter.continents.infrastructure.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataContinentJpa
  extends JpaRepository<ContinentEntity, Integer> {
}
