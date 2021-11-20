package com.folksdev.blog.controller;


import com.folksdev.blog.dto.CommentDto;
import com.folksdev.blog.dto.request.CreateCommentRequest;
import com.folksdev.blog.dto.request.UpdateCommentRequest;
import com.folksdev.blog.service.CommentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/v1/comment")
public class CommentController {
    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<CommentDto> getCommentWithId(@PathVariable String id) {
        CommentDto commentDto = commentService.getCommentById(id);
        return ResponseEntity.ok(commentDto);
    }

    @PostMapping(value = "/{userId}/{postId}")
    public ResponseEntity<CommentDto> createComment(@PathVariable String userId,
                                                    @PathVariable String postId,
                                                    @Valid @RequestBody CreateCommentRequest createCommentRequest) {
        CommentDto commentDto = commentService.createComment(userId,postId,createCommentRequest);
        return ResponseEntity.ok(commentDto);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<CommentDto> updateComment(@RequestBody String id,
                                                    @Valid @RequestBody UpdateCommentRequest updateCommentRequest) {
        CommentDto commentDto = commentService.updateComment(id,updateCommentRequest);
        return ResponseEntity.ok(commentDto);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> deletePost(@PathVariable String id) {
        return ResponseEntity.ok(commentService.deleteComment(id));
    }
}
