package com.virgen.peregrina.demo.data.entity

import com.virgen.peregrina.demo.data.model.UserModel
import com.virgen.peregrina.demo.service.implement.UserServiceImpl
import com.virgen.peregrina.demo.util.getLog
import org.apache.commons.logging.LogFactory
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "user")
data class User(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(unique = true, nullable = false, name = "user_id")
        val id: Long? = null,

        @Column(name = "user_uuid", unique = true, nullable = false)
        val uuid: String,

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
        val replicas: List<Replica>?
)


fun UserModel.toEntity() = try {
    val replicasAux = replicas?.map { it.toEntity().get() }
    val entity = User(
            uuid = uuid,
            name = name,
            lastName = lastName,
            email = email,
            address = address,
            city = city,
            country = country,
            cellphone = cellphone,
            telephone = telephone,
            photoUrl = photoUrl,
            replicas = replicasAux
    )
    Optional.of(entity)
} catch (ex: Exception) {
    getLog<UserModel>().info("UserModel.toEntity(): Exception -> $ex")
    Optional.empty<User>()
}

