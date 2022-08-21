package com.virgen.peregrina.demo.data.model

import com.virgen.peregrina.demo.data.entity.Replica

data class ReplicaModel(
        val id: Long? = null,
        val state: String,
        val requiredRestore: Boolean,
        val photoUrl: String? = null,
        val code: String
)

fun Replica.toModel() = ReplicaModel(
        id = id,
        state = state,
        requiredRestore = requiredRestore,
        photoUrl = photoUrl,
        code = code
)
