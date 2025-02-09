package com.virgen.peregrina.demo.data.entity

import com.virgen.peregrina.demo.data.model.ReplicaModel
import com.virgen.peregrina.demo.data.model.UserModel
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "REPLICA")
data class Replica(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false, name = "ID")
    val id: Long? = null,

//    @Column(name = "repl_required_restore")
//    val required_restore: Boolean,

    @Column(name = "PHOTO_URL")
    val photoUrl: String? = null,

    @Column(name = "CODE")
    val code: String,

    @Column(name = "BIRTHDATE")
    val birthdate: Date,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
        foreignKey = ForeignKey(name = "REPLICA_USER_FKEY"),
        name = "OWNER_ID",
        referencedColumnName = "ID"
    )
    val user: User,

//    @OneToMany(mappedBy = "REPLICA")
//    val pilgrimages: List<Pilgrimage>?
)


fun Replica.toModel(): ReplicaModel {
    return ReplicaModel(
        id = this.id ?: -1,
        photoUrl = photoUrl,
        code = code,
        birthdate = birthdate,
        user = user.toModel()
    )
}
