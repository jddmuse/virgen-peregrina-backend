package com.virgen.peregrina.demo.data.model

import java.time.LocalDate

data class ReplicaModel(
    val id: Long,
    val photoUrl: String?,
    val code: String,
    val birthdate: LocalDate,
    val user: UserModel,
    val pilgrimages: List<PilgrimageModel>?
)