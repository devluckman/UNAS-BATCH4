package com.unas.filmku.domain.request

data class RequestRegister(
    val firstName: String,
    val lastName: String,
    val email: String,
    val password: String,
    val confirmPass: String
)