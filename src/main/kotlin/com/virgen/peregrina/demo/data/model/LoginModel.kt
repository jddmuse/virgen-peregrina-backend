package com.virgen.peregrina.demo.data.model

data class LoginModel(
    val userId: Long,
    val userName: String,
    val userEmail: String,
    val userAddress: String,
    val userCity: String,
    val userCountry: String,
    val userCellphone: String
)