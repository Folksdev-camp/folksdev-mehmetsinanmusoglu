package com.folksdev.blog.dto

import javax.validation.constraints.*

data class CreateMovieRequest(

    @field: NotBlank
    val id: String,
    @field: NotBlank
    val name: String,
    @field: NotNull
    @field:Max(value = 100)
    @field: Min(value = 1)
    @field: Positive
    val age: Int,
    val director: String,
    val writers: String,
    val actors: String
)