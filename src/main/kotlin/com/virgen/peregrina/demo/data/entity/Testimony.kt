package com.virgen.peregrina.demo.data.entity

import com.virgen.peregrina.demo.data.model.TestimonyModel
import java.util.Date
import javax.persistence.*

@Entity
@Table(name = "TESTIMONY")
data class Testimony(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false, name = "ID")
    val id: Long? = null,

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(
//        foreignKey = ForeignKey(name = "TESTIMONY_USER_FKEY"),
//        name = "USER_ID",
//        referencedColumnName = "ID"
//    )
//    val user: User,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
        foreignKey = ForeignKey(name = "TESTIMONY_PILGRIMAGE_ID"),
        name = "PILGRIMAGE_ID",
        referencedColumnName = "ID"
    )
    val pilgrimage: Pilgrimage,

    @Column(name = "DATE")
    val date: Date?,

    @Column(name = "VALUE")
    val value: String
)

fun Testimony.toModel(): TestimonyModel {
    return TestimonyModel(
        id = id ?: -1,
        pilgrimageId = pilgrimage.id ?: -1,
        date = date ?: Date(),
        value = value
    )
}