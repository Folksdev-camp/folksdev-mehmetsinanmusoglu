package com.folksdev.blog.dto

data class CreateMovieDto(
    val name: String,
    val year: Int,
    val director: String,
    val writers: String,
    val actors: String
)
