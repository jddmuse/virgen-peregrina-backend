package com.virgen.peregrina.demo.data.model

import com.fasterxml.jackson.annotation.JsonProperty
import java.io.Serializable
import java.time.LocalDate

data class PilgrimageModel(
    @JsonProperty("id") val id: Long? = null,
    @JsonProperty("startDate") val startDate: LocalDate,
    @JsonProperty("endDate") val endDate: LocalDate,
    @JsonProperty("intention") val intention: String,
    @JsonProperty("replicaId") val replicaId: Long,
    @JsonProperty("userId") val userId: Long
)