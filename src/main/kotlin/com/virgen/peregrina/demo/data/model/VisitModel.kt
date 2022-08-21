package com.virgen.peregrina.demo.data.model

import com.virgen.peregrina.demo.data.converter.VisitConverter
import com.virgen.peregrina.demo.data.entity.Visit

data class VisitModel(
        val id: Long? = null,
        val date_start: String,
        val date_end: String,
        val description: String,
        val userId: Long,
        val replicaId: Long
)
