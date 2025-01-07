package com.aicha.hunter.web.vm.response;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter

public class PoduimResp {
    private UUID id;
    private String firstName;
    private String lastName;
    private double score;
}