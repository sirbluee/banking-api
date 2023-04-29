package com.example.bankingapi.api.user.web;

import com.example.bankingapi.api.user.UserService;
import com.example.bankingapi.base.BaseRest;
import com.github.pagehelper.PageInfo;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UserRestController {
    private final UserService userService;

    @PutMapping("/{id}")
    public BaseRest<?> updateIsDeleteStatusById(@PathVariable Integer id, @RequestBody IsDeleteDto dto){
       Integer isDeleteStatus = userService.isDeletestatus(id,dto.status());
        return BaseRest.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .message("Status was Changed.")
                .data(isDeleteStatus)
                .build();
    }
    @DeleteMapping("/{id}")
    public BaseRest<?> deleteUserById(@PathVariable Integer id){
        return BaseRest.builder()
                .status(true)
                .data(userService.findUserById(id))
                .status(true)
                .code(HttpStatus.OK.value())
                .message("Deleted Successfully")
                .data(id)
                .build();
    }

    @PostMapping
    public BaseRest<?> createNewUser(@RequestBody @Valid CreateUserDto createUserDto){
        log.info("Dto = {}", createUserDto);
    return BaseRest.builder()
            .data(userService.createNewUser(createUserDto))
            .status(true)
            .timestamp(LocalDateTime.now())
            .code(HttpStatus.OK.value())
            .message("Successfully.").build();
    }
    @GetMapping("/{id}")
    public BaseRest<?> findUserById(@PathVariable Integer id){
        return BaseRest.builder()
                .status(true)
                .data(userService.findUserById(id))
                .code(HttpStatus.OK.value())
                .message("Successfully")
                .build();
    }

    @GetMapping
    public BaseRest<?> findAllUser(@RequestParam(name = "page",required = false,defaultValue = "1") int page ,
                                   @RequestParam (name = "limit",required = false,defaultValue = "20") int limit ){
        PageInfo<UserDto> userDtoPageInfo = userService.findAllUser(page, limit);
        return BaseRest.builder()
                .status(true)
                .data(userDtoPageInfo)
                .code(HttpStatus.OK.value())
                .message("Successfully")
                .build();
    }
}
