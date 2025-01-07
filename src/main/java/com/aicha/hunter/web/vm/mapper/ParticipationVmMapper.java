package com.aicha.hunter.web.vm.mapper;

import com.aicha.hunter.domain.entity.Participation;
import com.aicha.hunter.web.vm.request.ParticipationRequest;
import com.aicha.hunter.web.vm.response.CompetitionResultsResponse;
import com.aicha.hunter.web.vm.response.ParticipationResponse;

import com.aicha.hunter.web.vm.response.PoduimResp;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ParticipationVmMapper {

    ParticipationVmMapper INSTANCE = Mappers.getMapper(ParticipationVmMapper.class);

    @Mapping(target = "user.id", source = "userId")
    @Mapping(target = "competition.id", source = "competitionId")
    Participation toParticipation(ParticipationRequest participationRequest);

    @Mapping(target = "username", source = "user.username")
    @Mapping(target = "code", source = "competition.code")
    @Mapping(target = "score", source = "score")
    ParticipationResponse toParticipationResponse(Participation participation);

    @Mapping(target = "firstName", source = "user.firstName")
    @Mapping(target = "lastName", source = "user.lastName")
    @Mapping(target = "score", source = "score")
    PoduimResp toPodiumResponse(Participation participations);
}