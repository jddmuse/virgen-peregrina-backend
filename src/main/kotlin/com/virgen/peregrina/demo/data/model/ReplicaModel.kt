package com.virgen.peregrina.demo.data.model

import com.virgen.peregrina.demo.data.entity.Replica
import com.virgen.peregrina.demo.util.getLog
import java.lang.Exception
import java.text.SimpleDateFormat
import java.util.Optional

data class ReplicaModel(
        val id: Long? = null,
        val requiredRestore: Boolean = false,
        val photoUrl: String? = null,
        val code: String,
        val received_date: String,
        var user: UserModel?
)

fun Replica.toModel(): Optional<ReplicaModel> = try {
    val sdf = SimpleDateFormat("dd/MM/yyyy")
    val model = ReplicaModel(
            id = id,
            requiredRestore = requiredRestore,
            photoUrl = photoUrl,
            code = code,
            received_date = sdf.format(received_date),
            user = user.toModel().get()
    )
    Optional.of(model)
} catch (ex: Exception) {
    getLog<UserModel>().info("Replica.toEntity(): Exception -> $ex")
    Optional.empty<ReplicaModel>()
}
