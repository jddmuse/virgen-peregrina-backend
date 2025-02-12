package com.virgen.peregrina.demo.data.request

import java.util.*

class CreateReplicaRequest(
    val photoUrl: String? = null,
    val code: String,
    val birthdate: Date,
    val ownerId: Long
)