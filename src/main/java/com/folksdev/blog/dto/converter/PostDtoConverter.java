package com.folksdev.blog.dto.converter;

import com.folksdev.blog.dto.CommentDto;
import com.folksdev.blog.dto.PostDto;
import com.folksdev.blog.dto.UserDto;
import com.folksdev.blog.model.Post;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PostDtoConverter {
    public PostDto convert(Post from){
        return new PostDto(
                from.getId(),
                from.getTitle(),
                from.getContext(),
                from.getCreateDate(),
                from.getUpdateDate(),
                new UserDto(
                        from.getUser().getId(),
                        from.getUser().getName(),
                        from.getUser().getEmail(),
                        from.getUser().getPassword()
                ),
                from.getComment()
                        .stream()
                        .map(c -> new CommentDto(
                                c.getId(),
                                c.getContext(),
                                c.getCreateDate(),
                                c.getUpdateDate()
                        )).collect(Collectors.toList())
        );
    }
    public List<PostDto> convertToPostDtoList(List<Post> posts) {
        return posts
                .stream()
                .map(this::convert)
                .collect(Collectors.toList());
    }
}
