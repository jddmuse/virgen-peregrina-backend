package com.virgen.peregrina.demo.data.model.replica

import com.virgen.peregrina.demo.data.model.pilgrimage.PilgrimageOnlyDatesModel
import com.virgen.peregrina.demo.data.model.user.UserLiteModel
import java.time.LocalDate

data class ReplicaModel(
    val id: Long,
    val photoUrl: String?,
    val code: String,
    val birthdate: LocalDate,
    val user: UserLiteModel,
    val pilgrimages: List<PilgrimageOnlyDatesModel>?
)