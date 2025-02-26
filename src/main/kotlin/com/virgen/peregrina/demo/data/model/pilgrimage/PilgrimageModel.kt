package com.virgen.peregrina.demo.data.model.pilgrimage

import com.fasterxml.jackson.annotation.JsonProperty
import com.virgen.peregrina.demo.data.model.replica.ReplicaLiteModel
import com.virgen.peregrina.demo.data.model.user.UserLiteModel
import java.time.LocalDate

data class PilgrimageModel(
    @JsonProperty("id") val id: Long? = null,
    @JsonProperty("startDate") val startDate: LocalDate,
    @JsonProperty("endDate") val endDate: LocalDate,
    @JsonProperty("intention") val intention: String,
    @JsonProperty("replica") val replica: ReplicaLiteModel,
    @JsonProperty("user") val user: UserLiteModel
)