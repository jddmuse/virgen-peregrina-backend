package com.virgen.peregrina.demo.data.model.pilgrimage

import com.fasterxml.jackson.annotation.JsonProperty
import java.time.LocalDate

data class PilgrimageOnlyDatesModel(
    @JsonProperty("startDate") val startDate: LocalDate,
    @JsonProperty("endDate") val endDate: LocalDate,
)