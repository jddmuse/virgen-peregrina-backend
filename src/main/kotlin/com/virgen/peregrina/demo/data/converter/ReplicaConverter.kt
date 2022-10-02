package com.virgen.peregrina.demo.data.converter

import com.virgen.peregrina.demo.data.entity.Replica
import com.virgen.peregrina.demo.data.model.ReplicaModel
import com.virgen.peregrina.demo.data.model.UserModel
import com.virgen.peregrina.demo.repository.UserRepository
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
                    user = user
            )
        }
        Optional.of(entity)
    } catch (ex: Exception) {
        getLog<UserModel>().info("$TAG toEntity(): Exception -> $ex")
        Optional.empty<Replica>()
    }

    override fun toModel(entity: Replica): Optional<ReplicaModel> = try {
        val sdf = SimpleDateFormat("dd/MM/yyyy")
        val model = entity.run {
            ReplicaModel(
                    id = id,
                    required_restore = required_restore,
                    photo_url = photo_url,
                    code = code,
                    received_date = sdf.format(received_date),
                    user_id = user.id!!,
                    user_name = user.name
            )
        }
        Optional.of(model)
    } catch (ex: Exception) {
        getLog<UserModel>().info("$TAG toEntity(): Exception -> $ex")
        Optional.empty<ReplicaModel>()
    }


}