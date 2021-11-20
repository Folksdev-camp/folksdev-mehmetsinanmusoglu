package com.folksdev.blog.dto.converter;

import com.folksdev.blog.dto.CommentDto;
import com.folksdev.blog.dto.PostDto;
import com.folksdev.blog.dto.UserDto;
import com.folksdev.blog.model.Comment;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CommentDtoConverter {
    public CommentDto convert(Comment from){
        return new CommentDto(
                from.getId(),
                from.getContext()
        );
    }
    public List<CommentDto> convertToCommentDtoList(List<Comment> comments) {
        return comments
                .stream()
                .map(this::convert)
                .collect(Collectors.toList());
    }
}
