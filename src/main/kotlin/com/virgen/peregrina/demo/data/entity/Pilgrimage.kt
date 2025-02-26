package com.virgen.peregrina.demo.data.entity

import com.virgen.peregrina.demo.data.model.pilgrimage.PilgrimageModel
import com.virgen.peregrina.demo.data.model.pilgrimage.PilgrimageOnlyDatesModel
import java.time.LocalDate
import javax.persistence.*

@Entity
@Table(name = "PILGRIMAGE")
class Pilgrimage(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false, name = "ID")
    val id: Long? = null,

    @Column(name = "START_DATE")
    val startDate: LocalDate,

    @Column(name = "END_DATE")
    val endDate: LocalDate,

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

    @Column(name = "STATUS")
    val status: String
)
