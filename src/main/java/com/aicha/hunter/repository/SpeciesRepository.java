package com.aicha.hunter.repository;

import com.aicha.hunter.domain.entity.Species;
import com.aicha.hunter.domain.enums.SpeciesType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface SpeciesRepository extends JpaRepository<Species, UUID> {

    List<Species> findByCategory(SpeciesType category);

    List<Species> findAll();

    boolean existsByName(String name);
}