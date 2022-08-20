package com.virgen.peregrina.demo.data.entity

import com.virgen.peregrina.demo.data.model.VisitModel
import java.text.SimpleDateFormat
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "visit")
class Visit(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(unique = true, nullable = false)
        val id: Long,

        @Column(name = "visi_date_start")
        val date_start: Date,

        @Column(name = "visi_date_end")
        val date_end: Date,

        @Column(name = "visi_description")
        val description: String,

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(foreignKey = ForeignKey(name = "visit_replica_fkey"), name = "repl_id", referencedColumnName = "repl_id")
        val replica: Replica,

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(foreignKey = ForeignKey(name = "visit_user_fkey"), name = "user_id", referencedColumnName = "user_id")
        val user: User
) {

    fun onRange(other: Visit): Boolean {
        return (other.date_start.after(date_start) && other.date_end.before(date_start))
                || (other.date_start.after(date_end) && other.date_end.before(date_end))
                || other.date_start == date_start || other.date_end == date_end
                || other.date_start == date_end || other.date_end == date_start
    }


}

fun VisitModel.toEntity() = run {
    val sdf = SimpleDateFormat("dd/MM/yyyy HH:mm")
    val dateStart = sdf.parse(date_start)
    val dateEnd = sdf.parse(date_end)
    Visit(
            id = id,
            date_start = dateStart,
            date_end = dateEnd,
            description = description,
            user = user.toEntity(),
            replica = replica.toEntity()
    )
}