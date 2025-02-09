package com.virgen.peregrina.demo.data.model

import java.util.*

data class PilgrimageModel(
    val replicaId: Long,
    val userId: Long,
    val startDate: Date,
    val endDate: Date,
    val intention: String
)