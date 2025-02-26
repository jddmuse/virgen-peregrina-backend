package com.virgen.peregrina.demo.data.entity

import org.hibernate.annotations.Where
import java.time.LocalDate
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
    val birthdate: LocalDate,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
        foreignKey = ForeignKey(name = "REPLICA_USER_FKEY"),
        name = "OWNER_ID",
        referencedColumnName = "ID"
    )
    val user: User,

    @OneToMany(mappedBy = "replica", cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    @Where(clause = "END_DATE >= CURRENT_DATE")
    val pilgrimages: List<Pilgrimage>? = null
)

