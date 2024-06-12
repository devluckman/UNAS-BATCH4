package com.unas.filmku.request

data class RequestLogin(
    val firstName: String,
    val lastName: String,
    val email: String,
    val password: String,
    val confirmPass: String
)