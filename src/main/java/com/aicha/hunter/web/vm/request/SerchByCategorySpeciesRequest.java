package com.aicha.hunter.web.vm.request;

import com.aicha.hunter.domain.enums.SpeciesType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SerchByCategorySpeciesRequest {
    @Enumerated(EnumType.STRING)
    private SpeciesType category;
}
