package com.folksdev.blog.dto.request

import javax.validation.constraints.NotBlank

data class CreateCategoryRequest @JvmOverloads constructor(
    @field: NotBlank
    val name: String,

    val post: String,
)
