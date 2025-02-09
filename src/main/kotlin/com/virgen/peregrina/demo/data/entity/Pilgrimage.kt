package com.virgen.peregrina.demo.data.entity

import com.virgen.peregrina.demo.data.model.PilgrimageModel
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "PILGRIMAGE")
class Pilgrimage(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false, name = "ID")
    val id: Long? = null,

    @Column(name = "START_DATE")
    val startDate: Date,

    @Column(name = "END_DATE")
    val endDate: Date,

    @Column(name = "INTENTION")
    val description: String,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
        foreignKey = ForeignKey(name = "PILGRIMAGE_REPLICA_FKEY"),
        name = "REPLICA_ID",
        referencedColumnName = "ID"
    )
    val replica: Replica,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
        foreignKey = ForeignKey(name = "PILGRIMAGE_USER_FKEY"),
        name = "USER_ID",
        referencedColumnName = "ID"
    )
    val user: User,

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(
//        foreignKey = ForeignKey(name = "pilgrimage_user_receiver_fkey"),
//        name = "user_id_receiver",
//        referencedColumnName = "user_id"
//    )
//    val receiver_user: User,

//    @Column(name = "pilg_is_returned")
//    val replica_is_returned: Boolean = false

    @Column(name = "STATUS")
    val status: String
) {

    fun onRange(other: Pilgrimage): Boolean {
        return (other.startDate.after(startDate) && other.startDate.before(endDate))
                || (other.endDate.after(startDate) && other.endDate.before(endDate))
                || other.startDate == startDate || other.endDate == endDate
                || other.startDate == endDate || other.endDate == startDate
    }

}
enum class EnumPilgrimageStatus {
    PENDING, PROGRESS, FINISHED
}

fun Pilgrimage.toModel(): PilgrimageModel {
    return PilgrimageModel(
        replicaId = replica.id ?: -1,
        userId = user.id ?: -1,
        startDate = startDate,
        endDate = endDate,
        intention = description
    )
}
