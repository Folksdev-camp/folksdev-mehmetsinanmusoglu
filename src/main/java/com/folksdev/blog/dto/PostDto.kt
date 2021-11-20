package com.folksdev.blog.dto

import com.fasterxml.jackson.annotation.JsonInclude
import java.time.LocalDateTime


data class PostDto @JvmOverloads constructor(
    val id: String,
    val title: String,
    val context: String,
    val createDate: LocalDateTime,
    val updateDate: LocalDateTime,

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    val user: UserDto?= null,
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    val comments: List<CommentDto>? = ArrayList(),
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    val category: List<CategoryDto>? = ArrayList()
) {

}
