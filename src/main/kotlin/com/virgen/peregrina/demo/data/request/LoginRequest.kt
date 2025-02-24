package com.virgen.peregrina.demo.data.request

import com.fasterxml.jackson.annotation.JsonProperty

data class LoginRequest(
    @JsonProperty("email") val email: String,
    @JsonProperty("pass") val pass: String
)