package com.virgen.peregrina.demo.data.model

import com.virgen.peregrina.demo.data.entity.User
import com.virgen.peregrina.demo.util.getLog
import java.lang.Exception
import java.util.Optional

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
        var replicas: List<ReplicaModel>? = null
)

fun User.toModel(): Optional<UserModel> = try {
    val model = UserModel(
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
            replicas = replicas?.map { it.toModel().get() }
    )
    Optional.of(model)
} catch (ex: Exception) {
    getLog<UserModel>().info("User.toModel(): Exception -> $ex")
    Optional.empty<UserModel>()
}

