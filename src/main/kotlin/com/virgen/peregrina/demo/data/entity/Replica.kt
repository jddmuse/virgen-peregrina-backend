package com.virgen.peregrina.demo.data.entity

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
    val required_restore: Boolean,

    @Column(name = "repl_photo_url")
    val photo_url: String? = null,

    @Column(name = "repl_code")
    val code: String,

    @Column(name = "repl_received_date")
    val received_date: Date,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
        foreignKey = ForeignKey(name = "replica_user_fkey"),
        name = "user_id",
        referencedColumnName = "user_id"
    )
    val user: User,

    @OneToMany(mappedBy = "replica")
    val pilgrimages: List<Pilgrimage>?
)
