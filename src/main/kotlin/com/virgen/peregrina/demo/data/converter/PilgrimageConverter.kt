package com.virgen.peregrina.demo.data.converter

import com.virgen.peregrina.demo.data.entity.Pilgrimage
import com.virgen.peregrina.demo.data.model.PilgrimageModel
import com.virgen.peregrina.demo.repository.ReplicaRepository
import com.virgen.peregrina.demo.repository.TestimonyRepository
import com.virgen.peregrina.demo.repository.UserRepository
import com.virgen.peregrina.demo.util.component.Converter
import com.virgen.peregrina.demo.util.METHOD_CALLED
import com.virgen.peregrina.demo.util.PARAMS
import com.virgen.peregrina.demo.util.PILGRIMAGE_CONVERTER_NAME
import com.virgen.peregrina.demo.util.PilgrimageState
import org.apache.commons.logging.LogFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Component
import java.text.SimpleDateFormat
import java.util.*

@Component(PILGRIMAGE_CONVERTER_NAME)
class PilgrimageConverter : Converter<PilgrimageModel, Pilgrimage> {
    companion object {
        private const val TAG = "PilgrimageConverter ->"
    }

    private val log = LogFactory.getLog(PilgrimageConverter::class.java)

    @Autowired
    @Qualifier("userRepository")
    private lateinit var userRepository: UserRepository

    @Autowired
    @Qualifier("replicaRepository")
    private lateinit var replicaRepository: ReplicaRepository

    @Autowired
    @Qualifier("testimonyRepository")
    private lateinit var testimonyRepository: TestimonyRepository

    override fun toEntity(model: PilgrimageModel): Optional<Pilgrimage> = try {
        log.debug("$TAG $METHOD_CALLED toEntity()")
        log.debug("$PARAMS $model")
        model.run {
            val sdf = SimpleDateFormat("dd-MM-yyyy") // Corregido el formato de fecha aquí
            val dateStart = sdf.parse(date_start)
            val dateEnd = sdf.parse(date_end)

            val user = userRepository.getReferenceById(user_id)
            val receiver_user = userRepository.getReferenceById(receiver_user_id)
            val replica = replicaRepository.getReferenceById(replica_id)

            val entity = Pilgrimage(
                id = id,
                date_start = dateStart,
                date_end = dateEnd,
                description = intention,
                user = user,
                replica = replica,
                receiver_user = receiver_user,
                replica_is_returned = replica_is_returned
            )
            Optional.of(entity)
        }
    } catch (ex: Exception) {
        log.error("$TAG toEntity(): Exception -> $ex")
        Optional.empty<Pilgrimage>()
    }

    override fun toModel(entity: Pilgrimage): Optional<PilgrimageModel> = try {
        log.debug("$TAG $METHOD_CALLED toModel()")
        log.debug("$PARAMS $entity")
        val currentDate = Calendar.getInstance().time
        val testimony = testimonyRepository.getTestimonyByPilgrimage(entity.id!!)

        val sdf = SimpleDateFormat("dd-MM-yyyy") // Corregido el formato de fecha aquí
        val dateStart: String = sdf.format(entity.date_start)
        val dateEnd: String = sdf.format(entity.date_end)

        val data = PilgrimageModel(
            id = entity.id,
            date_start = dateStart,
            date_end = dateEnd,
            intention = entity.description,
            user_id = entity.user.id!!,
            replica_id = entity.replica.id!!,
            receiver_user_id = entity.receiver_user.id!!,
            isFinished = currentDate.after(entity.date_end) || currentDate.equals(entity.date_end),
            city = entity.receiver_user.city ?: "",
            country = entity.receiver_user.country ?: "",
            state = when {
                currentDate.after(entity.date_end) || currentDate.equals(entity.date_end) -> PilgrimageState.FINISHED
                currentDate.before(entity.date_start) -> PilgrimageState.PENDING
                currentDate.equals(entity.date_start) || (currentDate.after(entity.date_start) && currentDate.before(entity.date_end)) -> PilgrimageState.IN_PROGRESS
                else -> ""
            },
            replica_is_returned = entity.replica_is_returned,
            have_testimony = testimony.isPresent,
            receiver_user_name = entity.receiver_user.name,
            receiver_user_telephone = entity.receiver_user.cellphone ?: "",
            receiver_user_email = entity.receiver_user.email,

            replica_owner_user_id = entity.replica.user.id,
            replica_owner_name_id = entity.replica.user.name,
            replica_owner_user_email = entity.replica.user.email,
            replica_owner_user_telephone = entity.replica.user.cellphone ?: "",
            replica_code = entity.replica.code ,
            replica_name = entity.replica.repl_name

        )
        Optional.of(data)
    } catch (ex: Exception) {
        log.error("$TAG toModel(): Exception -> $ex")
        Optional.empty<PilgrimageModel>()
    }
}
