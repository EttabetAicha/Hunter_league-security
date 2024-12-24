package com.aicha.hunter.web.vm.mapper;

import com.aicha.hunter.domain.entity.Hunt;
import com.aicha.hunter.web.vm.request.HuntRequest;
import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;

@Mapper(componentModel = "spring")
public interface HuntVmMapper {



    Hunt toHunt(HuntRequest huntRequest);


}

