package com.folksdev.blog;

import com.folksdev.blog.dto.CommentDto;
import com.folksdev.blog.dto.PostDto;
import com.folksdev.blog.dto.UserDto;
import com.folksdev.blog.dto.request.*;
import com.folksdev.blog.model.Comment;
import com.folksdev.blog.model.Post;
import com.folksdev.blog.model.User;

import java.time.LocalDateTime;
import java.util.List;

public class TestSupport {

    LocalDateTime dateTime = LocalDateTime.of(2020, 11, 20, 0, 0);

    // USER TEST SUPPORT
    public User generateUser() {
        return new User(
                "username",
                "user@mail.com",
                "password"
        );
    }

    public UserDto generateUserDto() {
        return new UserDto(
                "id",
                "username",
                "user@mail.com",
                "password"
        );
    }

    public List<User> generateListOfUser() {
        User user = generateUser();
        return List.of(user);
    }

    public List<UserDto> generateListOfUserDto() {
        UserDto userDto = generateUserDto();
        return List.of(userDto);
    }

    public CreateUserRequest generateCreateUserRequest() {
        return new CreateUserRequest(
                "username",
                "user@mail.com",
                "password"
        );
    }

    public UpdateUserRequest generateUpdateUserRequest() {
        return new UpdateUserRequest(
                "username",
                "user@mail.com",
                "password"
        );
    }
    public User generateUpdatedUser(String id, UpdateUserRequest updateUserRequest){
        return new User(
                id,
                updateUserRequest.getName(),
                updateUserRequest.getEmail(),
                updateUserRequest.getPassword()
        );
    }
    // POST TEST SUPPORT
    public Post generatePost() {
        return new Post(
                "title",
                "context",
                generateUser(),
                null
        );

    }

    public PostDto generatePostDto() {
        return new PostDto(
                "id",
                "title",
                "context",
                dateTime,
                dateTime,
                generateUserDto()
        );
    }
    public CreatePostRequest generateCreatePostRequest(){
        return new CreatePostRequest(
                "title",
                "context",
                dateTime,
                null,
                generateUser().getId()
        );
    }
    public UpdatePostRequest generateUpdatePostRequest(){
        return new UpdatePostRequest(
                "title",
                "context",
                dateTime,
                dateTime,
                null
        );
    }
    public List<Post> generateListOfPost() {
        Post post = generatePost();
        return List.of(post);
    }

    public List<PostDto> generateListOfPostDto() {
        PostDto postDto = generatePostDto();
        return List.of(postDto);
    }
    // Comment TEST SUPPORT

    public Comment generateComment(){
        return new Comment(
                "context",
                generatePost(),
                generateUser()
        );
    }
    public CommentDto generateCommentDto(){
        return new CommentDto(
                "id",
                "context",
                generateUserDto()
        );
    }
    public CreateCommentRequest generateCreateCommentRequest(){
        return new CreateCommentRequest(
                generateUserDto().getId(),
                "context"
        );
    }
    public UpdateCommentRequest generateUpdateCommentRequest(){
        return new UpdateCommentRequest("context");
    }
    public List<Comment> generateListOfComment() {
        Comment comment = generateComment();
        return List.of(comment);
    }

    public List<CommentDto> generateListOfCommentDto() {
        CommentDto commentDto = generateCommentDto();
        return List.of(commentDto);
    }

}
