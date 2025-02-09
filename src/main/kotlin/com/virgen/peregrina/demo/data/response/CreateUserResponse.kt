package com.virgen.peregrina.demo.data.response

data class CreateUserResponse(
    val id: Long,
    val name: String,
    val lastName: String,
    val email: String,
    val address: String,
    val city: String,
    val country: String,
    val cellphone: String,
    val telephone: String? = null,
    val photoUrl: String? = null,
)