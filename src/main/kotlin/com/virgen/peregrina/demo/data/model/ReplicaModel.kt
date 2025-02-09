package com.virgen.peregrina.demo.data.model

import com.virgen.peregrina.demo.data.entity.User
import java.util.*

data class ReplicaModel(
    val id: Long,
    val photoUrl: String?,
    val code: String,
    val birthdate: Date,
    val user: UserModel
)