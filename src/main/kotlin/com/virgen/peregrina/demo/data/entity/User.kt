package com.virgen.peregrina.demo.data.entity

import com.virgen.peregrina.demo.data.model.UserModel
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "user")
data class User(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(unique = true, nullable = false, name = "user_id")
        val id: Long?,

        @Column(name = "user_firebase_uid", unique = true, nullable = false)
        val firebaseUid: String,

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

        @Column(name = "user_role")
        val role: String
)


fun UserModel.toEntity() = User(
        id = id,
        firebaseUid = firebaseUid,
        name = name,
        lastName = lastName,
        email = email,
        address = address,
        city = city,
        country = country,
        cellphone = cellphone,
        telephone = telephone,
        photoUrl = photoUrl,
        role = role
)
