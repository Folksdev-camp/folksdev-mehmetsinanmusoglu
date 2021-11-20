package com.folksdev.blog.dto

import com.fasterxml.jackson.annotation.JsonInclude
import java.time.LocalDateTime

data class CommentDto @JvmOverloads constructor(
    val id: String,
    val context: String,
    val createDate: LocalDateTime,
    val updateDate: LocalDateTime,

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    val user: UserDto? = null,
)
