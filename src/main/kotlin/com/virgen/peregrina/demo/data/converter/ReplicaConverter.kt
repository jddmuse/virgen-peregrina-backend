package com.virgen.peregrina.demo.data.converter

import com.virgen.peregrina.demo.data.entity.Replica
import com.virgen.peregrina.demo.data.model.ReplicaModel
import com.virgen.peregrina.demo.data.model.UserModel
import com.virgen.peregrina.demo.repository.PilgrimageRepository
import com.virgen.peregrina.demo.repository.UserRepository
import com.virgen.peregrina.demo.util.PILGRIMAGE_CONVERTER_NAME
import com.virgen.peregrina.demo.util.PILGRIMAGE_REPOSITORY_NAME
import com.virgen.peregrina.demo.util.component.Converter
import com.virgen.peregrina.demo.util.getLog
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Component
import java.text.SimpleDateFormat
import java.util.*

@Component("replicaConverter")
class ReplicaConverter : Converter<ReplicaModel, Replica> {

    companion object {
        private const val TAG = "[ReplicaConverter] ->"
    }

    @Autowired
    @Qualifier("userRepository")
    private lateinit var userRepository: UserRepository

    @Autowired
    @Qualifier(PILGRIMAGE_REPOSITORY_NAME)
    private lateinit var pilgrimageRepository: PilgrimageRepository

    @Autowired
    @Qualifier(PILGRIMAGE_CONVERTER_NAME)
    private lateinit var pilgrimageConverter: PilgrimageConverter

    override fun toEntity(model: ReplicaModel): Optional<Replica> = try {
        val sdf = SimpleDateFormat("dd/MM/yyyy")
        val user = userRepository.getReferenceById(model.user_id)
        val entity = model.run {
            Replica(
                id = id,
                required_restore = required_restore,
                photo_url = photo_url,
                code = code,
                received_date = sdf.parse(received_date),
                user = user,
                pilgrimages = pilgrimages.map { pilgrimageConverter.toEntity(it).get() }
            )
        }
        Optional.of(entity)
    } catch (ex: Exception) {
        getLog<UserModel>().info("$TAG toEntity(): Exception -> $ex")
        Optional.empty<Replica>()
    }

    override fun toModel(entity: Replica): Optional<ReplicaModel> = try {
        val sdf = SimpleDateFormat("dd/MM/yyyy")
        val pilgrimage = pilgrimageRepository.getInProgressPilgrimageByReplica(entity.id!!)
        val available = pilgrimage.isEmpty
        val model = entity.run {
            ReplicaModel(
                id = id,
                required_restore = required_restore,
                photo_url = photo_url,
                code = code,
                received_date = sdf.format(received_date),
                user_id = user.id!!,
                user_name = user.name,
                user_cellphone = user.cellphone,
                user_country = user.country,
                user_city = user.city,
                user_email = user.email,
                isAvailable = available,
                pilgrimages = pilgrimages?.map {
                    pilgrimageConverter.toModel(it).get()
                } ?: emptyList()
            )
        }
        Optional.of(model)
    } catch (ex: Exception) {
        getLog<UserModel>().info("$TAG toModel(): Exception -> $ex")
        Optional.empty<ReplicaModel>()
    }


}