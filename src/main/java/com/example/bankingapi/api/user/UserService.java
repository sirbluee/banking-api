package com.example.bankingapi.api.user;
import com.example.bankingapi.api.user.web.CreateUserDto;
import com.example.bankingapi.api.user.web.IsDeleteDto;
import com.example.bankingapi.api.user.web.UserDto;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    UserDto createNewUser(CreateUserDto createUserDto);

    UserDto findUserById(Integer id);

    Integer deleteUserById(Integer id);

    Integer isDeletestatus(Integer id,  boolean status);

    PageInfo<UserDto> findAllUser(int page, int limit);
}
