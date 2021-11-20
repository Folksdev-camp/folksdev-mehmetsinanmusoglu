package com.folksdev.blog.dto.request

import java.time.LocalDateTime
import javax.validation.constraints.NotBlank

data class UpdateCommentRequest @JvmOverloads constructor(
    @field: NotBlank
    val context: String,
)
