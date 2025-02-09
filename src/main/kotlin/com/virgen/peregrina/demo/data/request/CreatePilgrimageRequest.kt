package com.virgen.peregrina.demo.data.request

import com.virgen.peregrina.demo.data.model.PilgrimageModel
import java.util.Date

data class CreatePilgrimageRequest(
    val replicaId: Long,
    val userId: Long,
    val startDate: Date,
    val endDate: Date,
    val intention: String
)

fun CreatePilgrimageRequest.toModel(): PilgrimageModel {
    return PilgrimageModel(
        replicaId = replicaId,
        userId = userId,
        startDate = startDate,
        endDate = endDate,
        intention = intention
    )
}