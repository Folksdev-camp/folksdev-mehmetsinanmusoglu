package com.folksdev.blog.service;

import com.folksdev.blog.TestSupport;
import com.folksdev.blog.dto.UserDto;
import com.folksdev.blog.dto.converter.UserDtoConverter;
;

import com.folksdev.blog.dto.request.CreateUserRequest;
import com.folksdev.blog.dto.request.UpdateUserRequest;
import com.folksdev.blog.exception.BaseNotFoundException;
import com.folksdev.blog.model.User;
import com.folksdev.blog.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class UserServiceTest extends TestSupport {

    private UserRepository userRepository;
    private UserDtoConverter userDtoConverter;

    private UserService userService;

    @BeforeEach
    void setUp() {
        userRepository = Mockito.mock(UserRepository.class);
        userDtoConverter = Mockito.mock(UserDtoConverter.class);

        userService = new UserService(userRepository, userDtoConverter);
    }

    @Test
    void testGetUserById_whenCalledExistId_itShouldReturnUserDto() {

        User user = generateUser();
        UserDto userDto = generateUserDto();

        Mockito.when(userRepository.findById("id")).thenReturn(Optional.of(user));
        Mockito.when(userDtoConverter.convert(user)).thenReturn(userDto);

        UserDto result = userService.getUserById("id");

        assertEquals(userDto, result);

        Mockito.verify(userRepository).findById("id");
        Mockito.verify(userDtoConverter).convert(user);
    }
    @Test
    void testGetUserById_whenCalledIdNotExists_itShouldThrowNotFoundException() {

        Mockito.when(userRepository.findById("id")).thenThrow(BaseNotFoundException.class);

        assertThrows(BaseNotFoundException.class, () -> userService.getUserById("id"));

        Mockito.verify(userRepository).findById("id");
        Mockito.verifyNoInteractions(userDtoConverter);
    }
    @Test
    void testGetUserList_itShouldReturnListOfUserDto() {

        List<User> repositoryList = generateListOfUser();
        List<UserDto> userDtoList = generateListOfUserDto();

        Mockito.when(userRepository.findAll())
                .thenReturn(repositoryList);
        Mockito.when(userDtoConverter.convertToUserDtoList(repositoryList))
                .thenReturn(userDtoList);

        List<UserDto> result = userService.getUserList();

        assertEquals(userDtoList, result);

        Mockito.verify(userRepository).findAll();
        Mockito.verify(userDtoConverter).convertToUserDtoList(repositoryList);
    }
    @Test
    void testCreateUser_whenPublisherIdNotExist_shouldThrowRuntimeException() {
        User user = generateUser();
        UserDto userDto = generateUserDto();

        CreateUserRequest createUserRequest = generateCreateUserRequest();

        Mockito.when(userDtoConverter.convert(user)).thenReturn(userDto);
        Mockito.when(userRepository.save(user)).thenReturn(user);

        UserDto result = userService.createUser(createUserRequest);

        assertEquals(userDto, result);

        Mockito.verify(userDtoConverter).convert(user);
        Mockito.verify(userRepository).save(user);
    }

    @Test
    void testDeleteUser_whenCalledInValidId_itShouldThrowNotFoundException() {

        Mockito.when(userRepository.findById("id")).thenThrow(BaseNotFoundException.class);

        assertThrows(BaseNotFoundException.class, () -> userService.getUserById("id"));

        Mockito.verify(userRepository).findById("id");
        Mockito.verifyNoInteractions(userDtoConverter);
    }
    @Test
    void testDeleteUser_whenCalledValidId_itShouldReturnString() {

        User user = generateUser();

        Mockito.when(userRepository.findById(user.getId())).thenReturn(Optional.of(user));

        String result = userService.deleteUser(user.getId());

        assertEquals("deleted", result);

        Mockito.verify(userRepository).findById(user.getId());
    }


    @Test
    void testUpdateUser_whenCalledValidRequest_itShouldReturnUserDto() {


        User updatedUser = generateUpdatedUser("id", generateUpdateUserRequest());
        UserDto userDto = generateUserDto();

        Mockito.when(userRepository.findById(updatedUser.getId())).thenReturn(Optional.of(updatedUser));
        Mockito.when(userRepository.save(updatedUser)).thenReturn(updatedUser);
        Mockito.when(userDtoConverter.convert(updatedUser)).thenReturn(userDto);

        UserDto result = userService.updateUser("id", generateUpdateUserRequest());

        assertEquals(userDto, result);

        Mockito.verify(userRepository).findById("id");
        Mockito.verify(userRepository).save(updatedUser);
        Mockito.verify(userDtoConverter).convert(updatedUser);
    }

    @Test
    void testUpdateUser_whenCalledIdInValid_itShouldThrowNotFoundException() {

        UpdateUserRequest request = generateUpdateUserRequest();

        Mockito.when(userRepository.findById("id")).thenThrow(BaseNotFoundException.class);

        assertThrows(BaseNotFoundException.class, () -> userService.updateUser("id", request));

        Mockito.verify(userRepository).findById("id");
        Mockito.verifyNoInteractions(userDtoConverter);
    }
}