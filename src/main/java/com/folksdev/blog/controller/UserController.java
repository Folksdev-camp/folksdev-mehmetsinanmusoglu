package com.folksdev.blog.controller;

import com.folksdev.blog.dto.UserDto;
import com.folksdev.blog.dto.request.CreateUserRequest;
import com.folksdev.blog.dto.request.UpdateUserRequest;
import com.folksdev.blog.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/v1/user")
public class UserController {
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<UserDto>> getUser() {
        List<UserDto> userDtoList = userService.getUserList();

        return ResponseEntity.ok(userDtoList);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<UserDto> getUserWithId(@PathVariable String id) {
        UserDto userDto = userService.getUserById(id);
        return ResponseEntity.ok(userDto);
    }

    @PostMapping
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody CreateUserRequest createUserRequest) {
        UserDto userDto = userService.createUser(createUserRequest);
        return ResponseEntity.ok(userDto);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<UserDto> updateUser(@RequestBody String id, @Valid @RequestBody UpdateUserRequest updateUserRequest) {
        UserDto userDto = userService.updateUser(id,updateUserRequest);
        return ResponseEntity.ok(userDto);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable String id) {
       return ResponseEntity.ok(userService.deleteUser(id));
    }

}
