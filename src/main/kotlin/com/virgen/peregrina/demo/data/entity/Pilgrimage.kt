package com.virgen.peregrina.demo.data.entity

import java.util.*
import javax.persistence.*

@Entity
@Table(name = "pilgrimage")
class Pilgrimage(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false, name = "pilg_id")
    val id: Long? = null,

    @Column(name = "pilg_date_start")
    val date_start: Date,

    @Column(name = "pilg_date_end")
    val date_end: Date,

    @Column(name = "pilg_intention")
    val description: String,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
        foreignKey = ForeignKey(name = "pilgrimage_replica_fkey"),
        name = "repl_id",
        referencedColumnName = "repl_id"
    )
    val replica: Replica,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
        foreignKey = ForeignKey(name = "pilgrimage_user_fkey"),
        name = "user_id",
        referencedColumnName = "user_id"
    )
    val user: User,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
        foreignKey = ForeignKey(name = "pilgrimage_user_receiver_fkey"),
        name = "user_id_receiver",
        referencedColumnName = "user_id"
    )
    val receiver_user: User,

    @Column(name = "pilg_is_returned")
    val replica_is_returned: Boolean = false
) {

    fun onRange(other: Pilgrimage): Boolean {
        return (other.date_start.after(date_start) && other.date_start.before(date_end))
                || (other.date_end.after(date_start) && other.date_end.before(date_end))
                || other.date_start == date_start || other.date_end == date_end
                || other.date_start == date_end || other.date_end == date_start
    }

}
