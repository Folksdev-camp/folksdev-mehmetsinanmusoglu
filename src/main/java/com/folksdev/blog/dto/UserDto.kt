package com.folksdev.blog.dto

import com.fasterxml.jackson.annotation.JsonInclude


data class UserDto @JvmOverloads constructor(
    val id: String,
    val name: String,
    val email: String,
    val password: String,

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    val posts: List<PostDto>? = ArrayList(),
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    val comments: List<CommentDto>? = ArrayList()
) {

}
