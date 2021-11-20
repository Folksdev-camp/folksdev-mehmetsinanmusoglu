package com.folksdev.blog.service;

import com.folksdev.blog.dto.UserDto;
import com.folksdev.blog.dto.converter.UserDtoConverter;
import com.folksdev.blog.dto.request.CreateUserRequest;
import com.folksdev.blog.dto.request.UpdateUserRequest;
import com.folksdev.blog.exception.BaseNotFoundException;
import com.folksdev.blog.model.User;
import com.folksdev.blog.repository.UserRepository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserDtoConverter userDtoConverter;

    public UserService(UserRepository userRepository, UserDtoConverter userDtoConverter) {
        this.userRepository = userRepository;
        this.userDtoConverter = userDtoConverter;
    }

    public List<UserDto> getUserList() {
        return userDtoConverter.convertToUserDtoList(userRepository.findAll());
    }

    public UserDto createUser(CreateUserRequest createUserRequest) {
        User user = new User(
                createUserRequest.getName(),
                createUserRequest.getEmail(),
                createUserRequest.getPassword()
        );
        return userDtoConverter.convert(userRepository.save(user));
    }
    public String deleteUser(String id){
        findUserById(id);
        userRepository.deleteById(id);
        return "deleted";
    }

    public UserDto updateUser(String id, UpdateUserRequest updateUserRequest){
        findUserById(id);
        User user = new User(id,
                updateUserRequest.getName(),
                updateUserRequest.getEmail(),
                updateUserRequest.getPassword());
        return userDtoConverter.convert(userRepository.save(user));
    }

    public UserDto getUserById(String id) {
        return userDtoConverter.convert(findUserById(id));
    }

    protected User findUserById(String id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new BaseNotFoundException("User could not find by id: " + id));
    }
}
