package com.folksdev.blog.service;

import com.folksdev.blog.dto.PostDto;
import com.folksdev.blog.dto.converter.PostDtoConverter;
import com.folksdev.blog.dto.request.CreatePostRequest;
import com.folksdev.blog.dto.request.UpdatePostRequest;
import com.folksdev.blog.exception.BaseNotFoundException;
import com.folksdev.blog.model.Post;
import com.folksdev.blog.repository.PostRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PostService {

    private final PostRepository postRepository;
    private final PostDtoConverter postDtoConverter;
    private final UserService userService;

    public PostService(PostRepository postRepository, PostDtoConverter postDtoConverter, UserService userService) {
        this.postRepository = postRepository;
        this.postDtoConverter = postDtoConverter;
        this.userService = userService;
    }
    public List<PostDto> getPostList(){
        return postDtoConverter.convertToPostDtoList(postRepository.findAll());
    }

    public String deletePost(String id){
        findPostById(id);
        postRepository.deleteById(id);
        return findPostById(id).getId() + "deleted";

    }
    public PostDto updatePost(String id, UpdatePostRequest updatePostRequest){
        Post p = findPostById(id);
        Post post = new Post(id,
                updatePostRequest.getTitle(),
                updatePostRequest.getContext(),
                p.getCreateDate(),
                updatePostRequest.getUpdatedAt(),
                p.getUser(),
                p.getComment(),
                p.getCategories());
        return postDtoConverter.convert(postRepository.save(post));
    }
    public PostDto createPost(String id,CreatePostRequest createPostRequest) {
       Post post = new Post(
               createPostRequest.getTitle(),
               createPostRequest.getContext(),
               LocalDateTime.now(),
               null,
               userService.findUserById(id),
               null
               );
       return postDtoConverter.convert(postRepository.save(post));
    }
    public PostDto getPostById(String id){
        return postDtoConverter.convert(findPostById(id));
    }
    protected Post findPostById(String id) {
        return postRepository.findById(id)
                .orElseThrow(() -> new BaseNotFoundException("Post could not find by id: " + id));
    }
}
