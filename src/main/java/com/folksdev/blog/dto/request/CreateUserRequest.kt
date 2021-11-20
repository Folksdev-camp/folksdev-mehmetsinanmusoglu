package com.folksdev.blog.dto.request

import javax.validation.constraints.*

data class CreateUserRequest @JvmOverloads constructor(

    @field: NotBlank
    val name: String,
    @field:Email
    val email: String,
    @field:NotBlank
    val password: String
)