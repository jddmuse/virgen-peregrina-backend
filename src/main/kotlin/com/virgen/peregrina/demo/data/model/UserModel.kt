package com.virgen.peregrina.demo.data.model

import com.virgen.peregrina.demo.data.entity.User
import com.virgen.peregrina.demo.data.request.toModel
import com.virgen.peregrina.demo.data.response.CreateUserResponse

data class UserModel(
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
    val pass: String
)

fun UserModel.toCreateUserResponse(): CreateUserResponse {
    return CreateUserResponse(
        id = this.id,
        name = this.name,
        lastName = this.lastName,
        email = this.email,
        address = this.address,
        city = this.city,
        country = this.country,
        cellphone = this.cellphone,
        telephone = this.telephone,
        photoUrl = this.photoUrl,
    )
}

fun UserModel.toEntity(): User {
    return User(
        id = this.id,
        name = this.name,
        lastName = this.lastName,
        email = this.email,
        address = this.address,
        city = this.city,
        country = this.country,
        cellphone = this.cellphone,
        telephone = this.telephone,
        photoUrl = this.photoUrl,
        pass = this.pass
    )
}