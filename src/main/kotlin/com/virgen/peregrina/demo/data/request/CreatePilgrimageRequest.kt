package com.virgen.peregrina.demo.data.request

import com.fasterxml.jackson.annotation.JsonProperty
import com.virgen.peregrina.demo.data.model.PilgrimageModel
import java.time.LocalDate

data class CreatePilgrimageRequest(
    @JsonProperty("replicaId") private val _replicaId: Long,
    @JsonProperty("userId") private val _userId: Long,
    @JsonProperty("startDate") private val _startDate: LocalDate,
    @JsonProperty("endDate") private val _endDate: LocalDate,
    @JsonProperty("intention") private val _intention: String
) {
    val replicaId get() = _replicaId
    val userId get() = _userId
    val startDate get() = _startDate
    val endDate get() = _endDate
    val intention get() = _intention.uppercase()
}

fun CreatePilgrimageRequest.toModel(): PilgrimageModel {
    return PilgrimageModel(
        replicaId = replicaId,
        userId = userId,
        startDate = startDate,
        endDate = endDate,
        intention = intention
    )
}