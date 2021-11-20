package com.folksdev.blog.dto.request

import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank

data class UpdateUserRequest @JvmOverloads constructor(
    @field: NotBlank
    val name: String,
    @field:Email
    val email: String,
    @field:NotBlank
    val password: String
)
