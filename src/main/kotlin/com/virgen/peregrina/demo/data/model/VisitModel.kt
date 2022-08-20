package com.virgen.peregrina.demo.data.model

import com.virgen.peregrina.demo.data.entity.Visit
import java.text.SimpleDateFormat

data class VisitModel(
        val id: Long,
        val date_start: String,
        val date_end: String,
        val description: String,
        val user: UserModel,
        val replica: ReplicaModel
)

fun Visit.toModel() = run {
    val sdf = SimpleDateFormat("dd/MM/yyyy HH:mm")
    val dateStart: String = sdf.format(date_start)
    val dateEnd: String = sdf.format(date_end)

    VisitModel(
            id = id,
            date_start = dateStart,
            date_end = dateEnd,
            description = description,
            user = user.toModel(),
            replica = replica.toModel()
    )
}
