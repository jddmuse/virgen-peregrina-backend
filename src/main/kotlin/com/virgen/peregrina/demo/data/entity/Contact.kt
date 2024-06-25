package com.virgen.peregrina.demo.data.entity

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
data class Contact(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    val direccion: String,
    val telefono1: String,
    val telefono2: String?,
    val email: String,
    val diasHabiles: String,
    val horarioHabil: String
)