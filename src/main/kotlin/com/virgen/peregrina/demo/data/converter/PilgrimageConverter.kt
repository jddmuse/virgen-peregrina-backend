package com.virgen.peregrina.demo.data.converter

import com.virgen.peregrina.demo.data.entity.Pilgrimage
import com.virgen.peregrina.demo.data.model.PilgrimageModel
import com.virgen.peregrina.demo.repository.ReplicaRepository
import com.virgen.peregrina.demo.repository.TestimonyRepository
import com.virgen.peregrina.demo.repository.UserRepository
import com.virgen.peregrina.demo.util.PILGRIMAGE_CONVERTER_NAME
import com.virgen.peregrina.demo.util.component.Converter
import org.apache.commons.logging.LogFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Component

@Component(PILGRIMAGE_CONVERTER_NAME)
class PilgrimageConverter : Converter<PilgrimageModel, Pilgrimage> {
    companion object {
        private const val TAG = "VisitConverter ->"
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


//    override fun toEntity(model: PilgrimageModel): Optional<Pilgrimage> = try {
//        log.debug("$TAG $METHOD_CALLED model2Entity()")
//        log.debug("$PARAMS $model")
//        model.run {
//            val sdf = SimpleDateFormat("dd/MM/yyyy")
//            val dateStart = sdf.parse(date_start)
//            val dateEnd = sdf.parse(date_end)
//
//            val user = userRepository.getReferenceById(user_id)
//            val receiver_user = userRepository.getReferenceById(receiver_user_id)
//            val replica = replicaRepository.getReferenceById(replica_id)
//
//            val entity = Pilgrimage(
//                id = id,
//                startDate = dateStart,
//                endDate = dateEnd,
//                description = intention,
//                user = user,
//                replica = replica,
//                status = ""
////                receiver_user = receiver_user,
////                replica_is_returned = replica_is_returned
//            )
//            Optional.of(entity)
//        }
//    } catch (ex: Exception) {
//        log.error("$TAG toEntity(): Exception -> $ex")
//        Optional.empty<Pilgrimage>()
//    }
//
//    override fun toModel(entity: Pilgrimage): Optional<PilgrimageModel> = try {
//        log.debug("$TAG $METHOD_CALLED entity2Model()")
//        log.debug("$PARAMS $entity")
//        val currentDate = Calendar.getInstance().time
//        val testimony = testimonyRepository.getTestimonyByPilgrimage(entity.id!!)
//        entity.run {
//            val sdf = SimpleDateFormat("dd/MM/yyyy")
//            val dateStart: String = sdf.format(startDate)
//            val dateEnd: String = sdf.format(endDate)
//            val data = PilgrimageModel(
//                id = id,
//                date_start = dateStart,
//                date_end = dateEnd,
//                intention = description,
//                user_id = user.id!!,
//                replica_id = replica.id!!,
//                receiver_user_id = -1,
//                isFinished = currentDate.after(endDate) || currentDate.equals(endDate),
//                city = "",
//                country = "",
//                state = when {
//                    currentDate.after(endDate) || currentDate.equals(endDate) -> PilgrimageState.FINISHED
//                    currentDate.before(startDate) -> PilgrimageState.PENDING
//                    currentDate.equals(startDate) || (currentDate.after(startDate) && currentDate.before(endDate)) -> PilgrimageState.IN_PROGRESS
//                    else -> ""
//                },
//                replica_is_returned = true,
//                have_testimony = testimony.isPresent,
//                receiver_user_name = "",
//                receiver_user_telephone = "",
//                receiver_user_email = "",
//
//                replica_owner_user_id = replica.user.id,
//                replica_owner_name_id = replica.user.name,
//                replica_owner_user_email = replica.user.email,
//                replica_owner_user_telephone = replica.user.cellphone ?: "",
//                replica_code = replica.code,
//
//            )
//            Optional.of(data)
//        }
//    } catch (ex: Exception) {
//        log.error("$TAG toModel(): Exception -> $ex")
//        Optional.empty<PilgrimageModel>()
//    }


}
