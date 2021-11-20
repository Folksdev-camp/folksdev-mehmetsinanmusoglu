package com.folksdev.blog.controller;


import com.folksdev.blog.dto.PostDto;
import com.folksdev.blog.dto.request.CreatePostRequest;
import com.folksdev.blog.dto.request.UpdatePostRequest;
import com.folksdev.blog.model.Post;
import com.folksdev.blog.service.PostService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/v1/post")
public class PostController {
    private PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }
    @GetMapping
    public ResponseEntity<List<PostDto>> getUser() {
        List<PostDto> postDtoList = postService.getPostList();

        return ResponseEntity.ok(postDtoList);

    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<PostDto> getPostWithId(@PathVariable String id) {
        PostDto postDto = postService.getPostById(id);
        return ResponseEntity.ok(postDto);
    }

    @PostMapping(value = "/{userid}")
    public ResponseEntity<PostDto> createPost(@PathVariable String userid,@Valid @RequestBody CreatePostRequest createPostRequest) {
        PostDto postDto = postService.createPost(userid,createPostRequest);
        return ResponseEntity.ok(postDto);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<PostDto> updatePost(@RequestBody String id, @Valid @RequestBody UpdatePostRequest updatePostRequest) {
        PostDto postDto = postService.updatePost(id,updatePostRequest);
        return ResponseEntity.ok(postDto);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> deletePost(@PathVariable String id) {
        return ResponseEntity.ok(postService.deletePost(id));
    }
}
