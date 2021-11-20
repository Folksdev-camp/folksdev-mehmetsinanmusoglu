package com.folksdev.blog.service;

import com.folksdev.blog.TestSupport;
import com.folksdev.blog.dto.PostDto;
import com.folksdev.blog.dto.converter.PostDtoConverter;
import com.folksdev.blog.model.Post;
import com.folksdev.blog.repository.PostRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

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
    @Test
    void testGetPostList_itShouldReturnListOfPostDto(){
        List<Post> postList = generateListOfPost();
        List<PostDto> postDtoList = generateListOfPostDto();

        Mockito.when(postRepository.findAll()).thenReturn(postList);
        Mockito.when(postDtoConverter.convertToPostDtoList(postList)).thenReturn(postDtoList);

        List<PostDto> result = postService.getPostList();

        assertEquals(postDtoList , result);

        Mockito.verify(postRepository).findAll();
        Mockito.verify(postDtoConverter).convertToPostDtoList(postList);
    }
    /*
    @Test
    void testUpdatePost_whenCalledValidRequest_itShouldReturn(){
        Post updatePost =
    }
*/
}
