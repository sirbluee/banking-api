package com.example.bankingapi.api.user;

import com.example.bankingapi.api.user.web.CreateUserDto;
import com.example.bankingapi.api.user.web.UserDto;
import com.github.pagehelper.PageInfo;
import org.mapstruct.Mapper;
import org.springframework.context.annotation.Configuration;


@Mapper(componentModel = "spring")
public interface UserMapStruct {
//    User createUserDtoToUser(CreateUserDto createUserDto);
    UserDto userToUserDto(User user);
    User userDtoToUser(UserDto userDto);
    User createUserDtoToUser(CreateUserDto createUserDto);
    PageInfo <UserDto> userPageInfoToUserDtoPageInfo(PageInfo<User> userPageInfo);
}
