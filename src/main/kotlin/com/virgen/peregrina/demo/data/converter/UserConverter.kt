package com.virgen.peregrina.demo.data.converter

import com.virgen.peregrina.demo.data.entity.User
import com.virgen.peregrina.demo.data.model.UserModel
import com.virgen.peregrina.demo.util.PILGRIMAGE_CONVERTER_NAME
import com.virgen.peregrina.demo.util.component.Converter
import com.virgen.peregrina.demo.util.getLog
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Component
import java.util.*

@Component("userConverter")
class UserConverter : Converter<UserModel, User> {

    companion object {
        private const val TAG = "[UserConverter] ->"
    }

    @Autowired
    @Qualifier("replicaConverter")
    private lateinit var replicaConverter: ReplicaConverter

    @Autowired
    @Qualifier(PILGRIMAGE_CONVERTER_NAME)
    private lateinit var pilgrimageConverter: PilgrimageConverter

    override fun toEntity(model: UserModel): Optional<User> = try {
        val replicasAux = model.replicas?.map {
            replicaConverter.toEntity(it).get()
        }
        val entity = model.run {
            User(
                id = id,
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
                replicas = replicasAux,
                isPilgrim = isPilgrim,
                pilgrimages = pilgrimages.map { pilgrimageConverter.toEntity(it).get() }
            )
        }
        Optional.of(entity)
    } catch (ex: Exception) {
        getLog<UserModel>().info("$TAG toEntity(): Exception -> $ex")
        Optional.empty<User>()
    }

    override fun toModel(entity: User): Optional<UserModel> = try {
        val model = entity.run {
            UserModel(
                id = id,
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
                replicas = replicas?.map {
                    replicaConverter.toModel(it).get()
                },
                isPilgrim = isPilgrim,
                pilgrimages = pilgrimages?.map {
                    pilgrimageConverter.toModel(it).get()
                } ?: emptyList()
            )
        }
        Optional.of(model)
    } catch (ex: java.lang.Exception) {
        getLog<UserModel>().info("$TAG toModel(): Exception -> $ex")
        Optional.empty<UserModel>()
    }
}