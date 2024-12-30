package com.aicha.hunter.service;

import com.aicha.hunter.domain.entity.Species;
import com.aicha.hunter.exception.exps.DuplicateResourceException;
import com.aicha.hunter.exception.exps.ResourceNotFoundException;
import com.aicha.hunter.repository.SpeciesRepository;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SpeciesService {

    private final SpeciesRepository speciesRepository;
    private final HuntService huntService;

    public List<Species> getSpeciesByCategory(Species species) {
        if (species.getCategory() == null) {
            return speciesRepository.findAll();
        }
        return speciesRepository.findByCategory(species.getCategory());
    }

    public Species addSpecies(Species species) {
        if (speciesRepository.existsByName(species.getName())) {
            throw new DuplicateResourceException("Species with name '" + species.getName() + "' already exists.");
        }
        return speciesRepository.save(species);
    }

    @Transactional
    public Species deleteSpeciesById(Species species) {
        Species speciesToDelete = speciesRepository.findById(species.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Species with id '" + species.getId() + "' does not exist."));
        try {
            huntService.deleteBySpecies(species.getId());
            speciesRepository.deleteById(species.getId());
        } catch (DataIntegrityViolationException e) {
            throw new IllegalStateException("Cannot delete species as it is referenced in other records.");
        }
        return speciesToDelete;
    }

    public Species updateSpecies(UUID id, Species updatedSpecies) {
        Species species = speciesRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Species with id '" + id + "' does not exist."));

        species.setName(updatedSpecies.getName());
        species.setCategory(updatedSpecies.getCategory());
        species.setMinimumWeight(updatedSpecies.getMinimumWeight());
        species.setDifficulty(updatedSpecies.getDifficulty());
        species.setPoints(updatedSpecies.getPoints());

        return speciesRepository.save(species);
    }

    public Species findById(UUID speciesId) {
        return speciesRepository.findById(speciesId)
                .orElseThrow(() -> new ResourceNotFoundException("Species with id '" + speciesId + "' does not exist."));
    }
}