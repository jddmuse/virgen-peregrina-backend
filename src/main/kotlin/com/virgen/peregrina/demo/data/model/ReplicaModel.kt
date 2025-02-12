package com.virgen.peregrina.demo.data.model

import java.util.*

data class ReplicaModel(
    val id: Long,
    val photoUrl: String?,
    val code: String,
    val birthdate: Date,
    val user: UserModel,
    val pilgrimages: List<PilgrimageModel>?
)