package com.virgen.peregrina.demo.data.entity

import com.virgen.peregrina.demo.data.model.ReplicaModel
import com.virgen.peregrina.demo.data.model.UserModel
import com.virgen.peregrina.demo.util.getLog
import java.text.SimpleDateFormat
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "replica")
data class Replica(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(unique = true, nullable = false, name = "repl_id")
        val id: Long? = null,

        @Column(name = "repl_required_restore")
        val requiredRestore: Boolean,

        @Column(name = "repl_photo_url")
        val photoUrl: String? = null,

        @Column(name = "repl_code")
        val code: String,

        @Column(name = "repl_received_date")
        val received_date: Date,

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(foreignKey = ForeignKey(name = "replica_user_fkey"), name = "user_id", referencedColumnName = "user_id")
        val user: User
)

fun ReplicaModel.toEntity(): Optional<Replica> = try {
    val sdf = SimpleDateFormat("dd/MM/yyyy")
    val entity = Replica(
            id = id,
            requiredRestore = requiredRestore,
            photoUrl = photoUrl,
            code = code,
            received_date = sdf.parse(received_date),
            user = user!!.toEntity().get()
    )
    Optional.of(entity)
} catch (ex: Exception) {
    getLog<UserModel>().info("ReplicaModel.toEntity(): Exception -> $ex")
    Optional.empty<Replica>()
}