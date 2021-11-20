package com.folksdev.blog.dto.converter;

import com.folksdev.blog.dto.CommentDto;
import com.folksdev.blog.dto.PostDto;
import com.folksdev.blog.dto.UserDto;
import com.folksdev.blog.model.User;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserDtoConverter {
    public UserDto convert(User from) {
        return new UserDto(
                from.getId(),
                from.getName(),
                from.getEmail(),
                from.getPassword(),
                from.getPosts()
                        .stream()
                        .map(p -> new PostDto(
                                p.getId(),
                                p.getTitle(),
                                p.getContext(),
                                p.getCreateDate(),
                                p.getUpdateDate()
                                ))
                        .collect(Collectors.toList()),
                from.getComments()
                        .stream()
                        .map(c -> new CommentDto(
                                c.getId(),
                                c.getContext(),
                                c.getCreateDate(),
                                c.getUpdateDate()
                        )).collect(Collectors.toList())
        );

    }
    public List<UserDto> convertToUserDtoList(List<User> users) {
        return users
                .stream()
                .map(this::convert)
                .collect(Collectors.toList());
    }
}
