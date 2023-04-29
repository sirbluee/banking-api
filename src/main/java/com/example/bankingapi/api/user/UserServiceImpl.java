package com.example.bankingapi.api.user;

import com.example.bankingapi.api.user.web.CreateUserDto;
import com.example.bankingapi.api.user.web.UserDto;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{
    private final UserMapper userMapper;
    private final UserMapStruct userMapStruct;

    @Override
    public UserDto createNewUser(CreateUserDto createUserDto) {
        User user = userMapStruct.createUserDtoToUser((createUserDto));
        UserDto userDto = userMapStruct.userToUserDto(user);
        userMapper.insert(user);
        return this.findUserById(user.getId());
    }

    @Override
    public UserDto findUserById(Integer id) {
        User user = userMapper.selectById(id).orElseThrow(()->
                new ResponseStatusException(HttpStatus.NOT_FOUND,
                        String.format("User %d is not found.",id)));
        return userMapStruct.userToUserDto(user);
    }

    @Override
    public Integer deleteUserById(Integer id) {
        boolean isFound = userMapper.existsById((id));

        if (isFound){
            userMapper.deletedById(id);
            return id;
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                String.format("User %d is not found.",id));
    }

    @Override
    public Integer isDeletestatus(Integer id, boolean status) {
        boolean isFound = userMapper.existsById((id));
        if (isFound){
            userMapper.updateById(id, status);
            return id;
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                String.format("User %d is not found.",id));
    }

    @Override
    public PageInfo<UserDto> findAllUser(int page, int limit) {
        PageInfo<UserDto> userDtoPageInfo = PageHelper.startPage(page,limit).doSelectPageInfo(userMapper::select);
        return userDtoPageInfo;
    }

}
