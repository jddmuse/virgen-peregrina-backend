package com.virgen.peregrina.demo.data.model

data class ContactDTO(
    val id: Long,
    val direccion: String,
    val telefono1: String,
    val telefono2: String?,
    val email: String,
    val diasHabiles: String,
    val horarioHabil: String
)