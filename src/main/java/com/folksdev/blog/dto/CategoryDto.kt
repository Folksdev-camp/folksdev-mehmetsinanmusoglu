package com.folksdev.blog.dto

import com.fasterxml.jackson.annotation.JsonInclude

data class CategoryDto @JvmOverloads constructor(
    val id: String? = "",
    val name: String,
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    val posts: List<PostDto>? = ArrayList()
    )
