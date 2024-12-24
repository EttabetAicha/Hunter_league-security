package com.aicha.hunter.web.vm.response;

import com.aicha.hunter.domain.enums.SpeciesType;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class SpeciesResponse {

    private UUID id;

    private String name;

    private SpeciesType category;
}
