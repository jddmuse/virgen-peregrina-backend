package com.virgen.peregrina.demo.data.request

import com.fasterxml.jackson.annotation.JsonProperty
import java.time.LocalDate
import java.util.*

class CreateReplicaRequest(
    @JsonProperty("code") val code: String,
    @JsonProperty("birthdate") val birthdate: LocalDate,
    @JsonProperty("ownerId") val ownerId: Long
)