package com.virgen.peregrina.demo.data.entity

import com.virgen.peregrina.demo.service.implement.VisitServiceImpl
import com.virgen.peregrina.demo.util.METHOD_CALLED
import com.virgen.peregrina.demo.util.PARAMS
import org.apache.commons.logging.LogFactory
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "visit")
class Visit(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(unique = true, nullable = false, name = "visi_id")
        val id: Long? = null,

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
        return (other.date_start.after(date_start) && other.date_start.before(date_end))
                || (other.date_end.after(date_start) && other.date_end.before(date_end))
                || other.date_start == date_start || other.date_end == date_end
                || other.date_start == date_end || other.date_end == date_start
    }

}
