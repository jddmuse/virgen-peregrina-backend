package com.virgen.peregrina.demo.data.model

data class ReplicaModel(
        val id: Long? = null,
        val required_restore: Boolean = false,
        val photo_url: String? = null,
        val code: String,
        val received_date: String,
        var user_id: Long,
        var user_name: String? = null
)

