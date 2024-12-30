package com.aicha.hunter.web.api;

import com.aicha.hunter.domain.entity.Species;
import com.aicha.hunter.service.SpeciesService;
import com.aicha.hunter.web.vm.request.SerchByCategorySpeciesRequest;
import com.aicha.hunter.web.vm.request.SpeciesRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/species")
@RequiredArgsConstructor
public class SpeciesAPI {

    private final SpeciesService speciesService;

    @GetMapping
    public ResponseEntity<List<Species>> getSpecies(@Valid SerchByCategorySpeciesRequest serchByCategorySpeciesRequest) {
        Species speciesEntity = new Species();
        speciesEntity.setCategory(serchByCategorySpeciesRequest.getCategory());
        List<Species> speciesList = speciesService.getSpeciesByCategory(speciesEntity);
        return ResponseEntity.ok(speciesList);
    }

    @PostMapping
    public ResponseEntity<Species> addSpecies(@Valid @RequestBody SpeciesRequest speciesRequest) {
        Species speciesEntity = new Species();
        speciesEntity.setName(speciesRequest.getName());
        speciesEntity.setCategory(speciesRequest.getCategory());
        speciesEntity.setMinimumWeight(speciesRequest.getMinimumWeight());
        speciesEntity.setDifficulty(speciesRequest.getDifficulty());
        speciesEntity.setPoints(speciesRequest.getPoints());
        Species species = speciesService.addSpecies(speciesEntity);
        return ResponseEntity.ok(species);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Species> deleteSpeciesById(@PathVariable UUID id) {
        Species species = new Species();
        species.setId(id);
        Species deletedSpecies = speciesService.deleteSpeciesById(species);
        return ResponseEntity.ok(deletedSpecies);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Species> updateSpecies(@PathVariable UUID id,
                                                 @Valid @RequestBody SpeciesRequest speciesRequest) {
        Species updatedSpecies = new Species();
        updatedSpecies.setName(speciesRequest.getName());
        updatedSpecies.setCategory(speciesRequest.getCategory());
        updatedSpecies.setMinimumWeight(speciesRequest.getMinimumWeight());
        updatedSpecies.setDifficulty(speciesRequest.getDifficulty());
        updatedSpecies.setPoints(speciesRequest.getPoints());

        Species savedSpecies = speciesService.updateSpecies(id, updatedSpecies);
        return ResponseEntity.ok(savedSpecies);
    }
}