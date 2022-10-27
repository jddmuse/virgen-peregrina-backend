package com.virgen.peregrina.demo.data.entity

import javax.persistence.*

@Entity
@Table(name = "user")
data class User(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false, name = "user_id")
    val id: Long? = null,

    @Column(name = "user_uuid", unique = true, nullable = true)
    val uuid: String?,

    @Column(name = "user_name")
    val name: String,

    @Column(name = "user_last_name")
    val lastName: String?,

    @Column(name = "user_email")
    val email: String,

    @Column(name = "user_address")
    val address: String?,

    @Column(name = "user_city")
    val city: String?,

    @Column(name = "user_country")
    val country: String?,

    @Column(name = "user_cellphone")
    val cellphone: String?,

    @Column(name = "user_telephone")
    val telephone: String?,

    @Column(name = "user_photo_url")
    val photoUrl: String?,

    @OneToMany(mappedBy = "user")
    val replicas: List<Replica>?,

    @Column(name = "user_is_pilgrim")
    val isPilgrim: Boolean?,

    @OneToMany(mappedBy = "user")
    val pilgrimages: List<Pilgrimage>?
)