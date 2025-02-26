package com.virgen.peregrina.demo.data.model.pilgrimage

import com.virgen.peregrina.demo.data.entity.Pilgrimage
import com.virgen.peregrina.demo.data.model.replica.liteModel
import com.virgen.peregrina.demo.data.model.user.liteModel


fun Pilgrimage.model(): PilgrimageModel {
    return PilgrimageModel(
        id = id ?: -1,
        replica = replica.liteModel(),
        user = user.liteModel(),
        startDate = startDate,
        endDate = endDate,
        intention = description
    )
}

fun Pilgrimage.onlyDatesModel(): PilgrimageOnlyDatesModel {
    return PilgrimageOnlyDatesModel(
        startDate = startDate,
        endDate = endDate
    )
}