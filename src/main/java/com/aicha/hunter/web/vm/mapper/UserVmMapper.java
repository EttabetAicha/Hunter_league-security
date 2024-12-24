package com.aicha.hunter.web.vm.mapper;

import com.aicha.hunter.domain.entity.User;
import com.aicha.hunter.web.vm.request.LoginRequest;
import com.aicha.hunter.web.vm.request.RegisterRequest;
import com.aicha.hunter.web.vm.request.UserRequest;
import com.aicha.hunter.web.vm.request.UserSearchRequest;
import com.aicha.hunter.web.vm.response.UserHistoryResponse;
import com.aicha.hunter.web.vm.response.UserResponse;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserVmMapper {






    User toUser(UserSearchRequest userSearchRequest);
    User toUser(LoginRequest loginRequest);
    User toUser(UserRequest userRequest);
    User toUser(RegisterRequest registerRequest);
    UserResponse toUserResponse(User user);
    List<UserResponse> toUsersResponceList(Page<User> users);
    List<UserHistoryResponse> toUserHistoryResponse(Page<User> users);

}

