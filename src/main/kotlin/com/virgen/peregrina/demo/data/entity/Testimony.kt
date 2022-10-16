package com.virgen.peregrina.demo.data.entity

import java.util.Date
import javax.persistence.*

@Entity
@Table(name = "testimony")
data class Testimony(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false, name = "test_id")
    val id: Long? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
        foreignKey = ForeignKey(name = "testimony_user_fkey"),
        name = "user_id",
        referencedColumnName = "user_id"
    )
    val user: User,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
        foreignKey = ForeignKey(name = "testimony_pilgrimage_fkey"),
        name = "pilg_id",
        referencedColumnName = "pilg_id"
    )
    val pilgrimage: Pilgrimage,

    @Column(name = "test_date")
    val date: Date?,

    @Column(name = "test_value")
    val value: String
)