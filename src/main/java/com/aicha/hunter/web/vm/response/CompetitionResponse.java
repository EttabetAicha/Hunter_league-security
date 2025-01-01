package com.aicha.hunter.web.vm.response;



import com.aicha.hunter.domain.enums.SpeciesType;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;


@Getter
@Setter
public class CompetitionResponse {


    private UUID id;

    private String location;
    private LocalDateTime date;
    private SpeciesType speciesType;
    private Integer minParticipants;
    private Integer maxParticipants;
    private Boolean openRegistration;
    private Integer participationCount = 0;








}
