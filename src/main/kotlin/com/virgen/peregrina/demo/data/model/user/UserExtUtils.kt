package com.virgen.peregrina.demo.data.model.user

import com.virgen.peregrina.demo.data.entity.User
import com.virgen.peregrina.demo.data.model.LoginModel
import com.virgen.peregrina.demo.data.request.CreateUserRequest
import com.virgen.peregrina.demo.data.response.CreateUserResponse

fun UserModel.createUserResponse(): CreateUserResponse {
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

fun CreateUserRequest.entity(): User {
    return User(
        id = -1,
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

fun UserModel.entity(): User {
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

fun User.model(): UserModel {
    return UserModel(
        id = this.id!!,
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

fun User.liteModel(): UserLiteModel {
    return UserLiteModel(
        id = this.id!!,
        name = this.name,
        lastName = this.lastName,
        email = this.email,
        address = this.address,
        city = this.city,
        country = this.country,
        cellphone = this.cellphone
    )
}

fun User.loginModel() : LoginModel {
    return LoginModel(
        userId = this.id ?: -1,
        userName = this.name,
        userEmail = this.email,
        userAddress = this.address,
        userCity = this.city,
        userCountry = this.country,
        userCellphone = this.cellphone
    )
}