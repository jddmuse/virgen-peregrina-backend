package com.virgen.peregrina.demo.data.model

import com.virgen.peregrina.demo.data.entity.User

data class UserModel(
        val id: Long? = null,
        val uuid: String,
        val name: String,
        val lastName: String?,
        val email: String,
        val address: String?,
        val city: String?,
        val country: String?,
        val cellphone: String?,
        val telephone: String?,
        val photoUrl: String?,
        val role: String
)

fun User.toModel() = UserModel(
        id = id,
        uuid = uuid,
        name = name,
        lastName = lastName,
        email = email,
        address = address,
        city = city,
        country = country,
        cellphone = cellphone,
        telephone = telephone,
        photoUrl = photoUrl,
        role = role
)

