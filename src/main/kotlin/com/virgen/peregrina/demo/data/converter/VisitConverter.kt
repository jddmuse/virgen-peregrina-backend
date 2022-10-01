package com.virgen.peregrina.demo.data.converter

import com.virgen.peregrina.demo.data.entity.Visit
import com.virgen.peregrina.demo.data.model.VisitModel
import com.virgen.peregrina.demo.repository.ReplicaRepository
import com.virgen.peregrina.demo.repository.UserRepository
import com.virgen.peregrina.demo.util.component.Converter
import com.virgen.peregrina.demo.util.METHOD_CALLED
import com.virgen.peregrina.demo.util.PARAMS
import org.apache.commons.logging.LogFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Component
import java.text.SimpleDateFormat
import java.util.*

@Component("visitConverter")
class VisitConverter : Converter<VisitModel, Visit> {
    companion object {
        private const val TAG = "VisitConverter ->"
    }

    private val log = LogFactory.getLog(VisitConverter::class.java)

    @Autowired
    @Qualifier("userRepository")
    private lateinit var userRepository: UserRepository

    @Autowired
    @Qualifier("replicaRepository")
    private lateinit var replicaRepository: ReplicaRepository


    override fun toEntity(model: VisitModel): Optional<Visit> = try {
        log.debug("$TAG $METHOD_CALLED model2Entity()")
        log.debug("$PARAMS $model")
        model.run {
            val sdf = SimpleDateFormat("dd/MM/yyyy")
            val dateStart = sdf.parse(date_start)
            val dateEnd = sdf.parse(date_end)

            val user = userRepository.getReferenceById(userId)
            val replica = replicaRepository.getReferenceById(replicaId)

            val entity = Visit(
                    id = id,
                    date_start = dateStart,
                    date_end = dateEnd,
                    description = description,
                    user = user,
                    replica = replica
            )
            Optional.of(entity)
        }
    } catch (ex: Exception) {
        log.error("$TAG model2Entity(): Exception -> $ex")
        Optional.empty<Visit>()
    }

    override fun toModel(entity: Visit): Optional<VisitModel> = try {
        log.debug("$TAG $METHOD_CALLED entity2Model()")
        log.debug("$PARAMS $entity")
        entity.run {
            val sdf = SimpleDateFormat("dd/MM/yyyy")
            val dateStart: String = sdf.format(date_start)
            val dateEnd: String = sdf.format(date_end)
            val data = VisitModel(
                    id = id,
                    date_start = dateStart,
                    date_end = dateEnd,
                    description = description,
                    userId = user.id!!,
                    replicaId = replica.id!!
            )
            Optional.of(data)
        }
    } catch (ex: Exception) {
        log.error("$TAG entity2Model(): Exception -> $ex")
        Optional.empty<VisitModel>()
    }


}
