package com.folksdev.blog.service;

import com.folksdev.blog.TestSupport;
import com.folksdev.blog.dto.converter.PostDtoConverter;
import com.folksdev.blog.repository.PostRepository;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

class PostServiceTest extends TestSupport {
    private PostRepository postRepository;
    private PostDtoConverter postDtoConverter;
    private UserService userService;

    private PostService postService;

    @BeforeEach
    void setUp() {
        postRepository = Mockito.mock(PostRepository.class);
        postDtoConverter = Mockito.mock(PostDtoConverter.class);
        userService = Mockito.mock(UserService.class);

        postService = new PostService(postRepository,postDtoConverter,userService);
    }

}