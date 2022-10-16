package com.virgen.peregrina.demo.data.model

data class TestimonyModel(
    val id: Long? = null,
    var date: String? = null,
    val user_id: Long,
    val user_name: String?,
    val pilgrimage_id: Long,
    val value: String
)