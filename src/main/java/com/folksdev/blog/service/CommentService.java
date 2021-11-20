package com.folksdev.blog.service;

import com.folksdev.blog.dto.CommentDto;
import com.folksdev.blog.dto.converter.CommentDtoConverter;
import com.folksdev.blog.dto.request.CreateCommentRequest;
import com.folksdev.blog.dto.request.UpdateCommentRequest;
import com.folksdev.blog.exception.BaseNotFoundException;
import com.folksdev.blog.model.Comment;
import com.folksdev.blog.repository.CommentRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CommentService {
    private final CommentRepository commentRepository;
    private final CommentDtoConverter commentDtoConverter;
    private final PostService postService;
    private final UserService userService;

    public CommentService(CommentRepository commentRepository, CommentDtoConverter commentDtoConverter, PostService postService, UserService userService) {
        this.commentRepository = commentRepository;
        this.commentDtoConverter = commentDtoConverter;
        this.postService = postService;
        this.userService = userService;
    }

    public List<CommentDto> getCommentList() {
        return commentDtoConverter.convertToCommentDtoList(commentRepository.findAll());
    }

    public String deleteComment(String id) {
        findCommentById(id);
        commentRepository.deleteById(id);
        return findCommentById(id).getId() + "deleted";
    }

    public CommentDto createComment(String userid, String postid, CreateCommentRequest createCommentRequest) {
        Comment comment = new Comment(
                createCommentRequest.getContext(),
                postService.findPostById(postid),
                userService.findUserById(userid));
        return commentDtoConverter.convert(commentRepository.save(comment));
    }

    public CommentDto updateComment(String id, UpdateCommentRequest updateCommentRequest) {
        Comment c = findCommentById(id);
        Comment comment = new Comment(id,
                updateCommentRequest.getContext(),
                c.getPost(),
                c.getUser());
        return commentDtoConverter.convert(commentRepository.save(comment));
    }

    public CommentDto getCommentById(String id) {
        findCommentById(id);
        return commentDtoConverter.convert(commentRepository.getById(id));
    }

    protected Comment findCommentById(String id) {
        return commentRepository.findById(id)
                .orElseThrow(() -> new BaseNotFoundException("Post could not find by id: " + id));
    }
}
