package com.aicha.hunter.web.api;

import com.aicha.hunter.domain.entity.Competition;

import com.aicha.hunter.repository.dto.CompetitionRepoDTO;
import com.aicha.hunter.service.CompetitionService;
import com.aicha.hunter.web.vm.mapper.CompetitionVmMapper;
import com.aicha.hunter.web.vm.request.CompetitionRequest;
import com.aicha.hunter.web.vm.response.CompetitionResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/competitions")
@RequiredArgsConstructor
public class CompetitionAPI {

    private final CompetitionVmMapper competitionVmMapper;
    private final CompetitionService competitionService;


    @PostMapping
    public ResponseEntity<CompetitionResponse> addCompetition(
            @Valid @RequestBody CompetitionRequest competitionRequest) {

        Competition competitionEntity = competitionVmMapper.toCompetition(competitionRequest);
        Competition competition = competitionService.addCompetition(competitionEntity);

        return ResponseEntity.ok(competitionVmMapper.toCompetitionResponse(competition));
    }


    @GetMapping
    public ResponseEntity<Page<CompetitionRepoDTO>> getCompetitions(@RequestParam(defaultValue = "0") int page,
                                                                    @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<CompetitionRepoDTO> competitionDTOs = competitionService.getAllCompetitions(pageable);
        return ResponseEntity.ok(competitionDTOs);
    }
    
}