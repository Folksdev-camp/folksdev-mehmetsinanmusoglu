package com.folksdev.blog.dto.request

import java.time.LocalDateTime
import javax.validation.constraints.NotBlank

data class CreateCommentRequest @JvmOverloads constructor(
    @field: NotBlank
    val userId: String,

    @field: NotBlank
    val context: String,

    @field: NotBlank
    val createDate: LocalDateTime
)
