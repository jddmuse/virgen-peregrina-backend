package com.virgen.peregrina.demo.data.model

import com.virgen.peregrina.demo.data.entity.User
import com.virgen.peregrina.demo.util.getLog
import java.lang.Exception
import java.util.Optional

data class UserModel(
    val id: Long? = null,
    val uuid: String?,
    val name: String,
    val lastName: String?,
    val email: String,
    val address: String?,
    val city: String?,
    val country: String?,
    val cellphone: String?,
    val telephone: String?,
    val photoUrl: String?,
    var replicas: List<ReplicaModel>? = null,
    val isPilgrim: Boolean?,
    val pilgrimages: List<PilgrimageModel> = emptyList()
)

