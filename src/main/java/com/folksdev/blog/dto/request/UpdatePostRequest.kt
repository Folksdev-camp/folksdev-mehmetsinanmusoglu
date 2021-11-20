package com.folksdev.blog.dto.request

import java.time.LocalDateTime
import javax.validation.constraints.NotBlank

data class UpdatePostRequest @JvmOverloads constructor(
    @field: NotBlank
    val title: String,
    @field: NotBlank
    val context: String,
    @field: NotBlank
    val createdAt: LocalDateTime,
    @field: NotBlank
    val updatedAt: LocalDateTime,
    val category:String?
)
