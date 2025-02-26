package com.virgen.peregrina.demo.data.entity

import com.virgen.peregrina.demo.data.model.user.UserModel
import javax.persistence.*

@Entity
@Table(name = "USER")
data class User(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false, name = "ID")
    val id: Long? = null,

//    @Column(name = "user_uuid", unique = true, nullable = true)
//    val uuid: String?,

    @Column(name = "NAME")
    val name: String,

    @Column(name = "LAST_NAME")
    val lastName: String,

    @Column(name = "EMAIL", unique = true, nullable = false)
    val email: String,

    @Column(name = "ADDRESS")
    val address: String,

    @Column(name = "CITY")
    val city: String,

    @Column(name = "COUNTRY")
    val country: String,

    @Column(name = "PHONE")
    val cellphone: String,

    @Column(name = "HOME_PHONE")
    val telephone: String?,

    @Column(name = "PHOTO_URL")
    val photoUrl: String?,

    @Column(name = "PASS")
    val pass: String

//    @OneToMany(mappedBy = "USER")
//    val replicas: List<Replica>?,

//    @Column(name = "user_is_pilgrim")
//    val isPilgrim: Boolean?,

//    @OneToMany(mappedBy = "USER")
//    val pilgrimages: List<Pilgrimage>?
)