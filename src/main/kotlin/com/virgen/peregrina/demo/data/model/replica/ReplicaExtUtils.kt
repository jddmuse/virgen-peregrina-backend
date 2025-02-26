package com.virgen.peregrina.demo.data.model.replica

import com.virgen.peregrina.demo.data.entity.Replica
import com.virgen.peregrina.demo.data.model.pilgrimage.onlyDatesModel
import com.virgen.peregrina.demo.data.model.user.liteModel

fun Replica.model(): ReplicaModel {
    return ReplicaModel(
        id = this.id ?: -1,
        photoUrl = photoUrl,
        code = code,
        birthdate = birthdate,
        user = user.liteModel(),
        pilgrimages = pilgrimages?.map { it.onlyDatesModel() } ?: emptyList()
    )
}

fun Replica.liteModel(): ReplicaLiteModel {
    return ReplicaLiteModel(
        code = code,
        birthdate = birthdate
    )
}