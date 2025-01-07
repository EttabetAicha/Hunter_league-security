    package com.aicha.hunter.web.api;

    import com.aicha.hunter.domain.entity.Competition;
    import com.aicha.hunter.domain.entity.Participation;

    import com.aicha.hunter.service.ParticipationService;
    import com.aicha.hunter.web.vm.request.ParticipationRequest;
    import com.aicha.hunter.web.vm.mapper.ParticipationVmMapper;
    import com.aicha.hunter.web.vm.response.CompetitionResultsResponse;
    import com.aicha.hunter.web.vm.response.ParticipationResponse;
    import org.springframework.data.domain.PageRequest;
    import org.springframework.data.domain.Pageable;
    import org.springframework.data.domain.Sort;
    import org.springframework.http.ResponseEntity;
    import org.springframework.security.access.prepost.PreAuthorize;
    import org.springframework.web.bind.annotation.*;
    import jakarta.validation.Valid;

    import java.util.List;
    import java.util.UUID;
    import java.util.stream.Collectors;

    @RestController
    @RequestMapping("/api/participations")
    public class ParticipationAPI {

        private final ParticipationService participationService;
        private final ParticipationVmMapper participationVmMapper;


        public ParticipationAPI(ParticipationService participationService, ParticipationVmMapper participationVmMapper) {
            this.participationService = participationService;
            this.participationVmMapper = participationVmMapper;
        }


        @PostMapping("/register")
        public ResponseEntity<?> registerParticipation(@Valid @RequestBody ParticipationRequest participationRequest) {
            Participation participation = participationVmMapper.toParticipation(participationRequest);

            Participation savedParticipation = participationService.registerParticipant(participation);
            ParticipationResponse participationResponse = participationVmMapper.toParticipationResponse(savedParticipation);
            return ResponseEntity.ok(participationResponse);
        }

        @GetMapping("/results/{userId}/{competitionId}")
        public ResponseEntity<List<CompetitionResultsResponse>> getCompetitionResults(@PathVariable UUID userId, @PathVariable UUID competitionId) {
            List<Participation> participations = participationService.getParticipationResults(userId, competitionId);

            List<CompetitionResultsResponse> results = participations.stream().map(participation -> {
                Competition competition = participation.getCompetition();
                CompetitionResultsResponse response = new CompetitionResultsResponse();
                response.setId(competition.getId());
                response.setLocation(competition.getLocation());
                response.setDate(competition.getDate());
                response.setScore(participation.getScore());
                return response;
            }).collect(Collectors.toList());

            return ResponseEntity.ok(results);
        }


        @GetMapping("/podium/{competitionId}")
        public List<CompetitionResultsResponse> getCompetitionPodium(@PathVariable UUID competitionId) {
            List<Participation> participations = participationService.getCompetitionPodium(competitionId);
            return participationVmMapper.toParticipationResultResponse(participations);
        }
        
        @GetMapping
        public ResponseEntity<List<ParticipationResponse>> getAllParticipations() {
            List<Participation> participations = participationService.getAllParticipations();
            List<ParticipationResponse> participationResponses = participations.stream()
                    .map(participationVmMapper::toParticipationResponse)
                    .collect(Collectors.toList());
            return ResponseEntity.ok(participationResponses);
        }

    }
