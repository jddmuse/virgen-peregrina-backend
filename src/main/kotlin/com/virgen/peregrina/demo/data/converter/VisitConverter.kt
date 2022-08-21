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


    override fun toEntity(model: VisitModel): Visit? {
        try {
            log.debug("$TAG $METHOD_CALLED model2Entity()")
            log.debug("$PARAMS $model")
            with(model) {
                val sdf = SimpleDateFormat("dd/MM/yyyy")
                val dateStart = sdf.parse(date_start)
                val dateEnd = sdf.parse(date_end)

                val user = userRepository.getReferenceById(userId)
                val replica = replicaRepository.getReferenceById(replicaId)

                return Visit(
                        id = id,
                        date_start = dateStart,
                        date_end = dateEnd,
                        description = description,
                        user = user,
                        replica = replica
                )
            }
        } catch (ex: Exception) {
            log.error("$TAG model2Entity(): Exception -> $ex")
            return null
        }
    }

    override fun toModel(entity: Visit): VisitModel? {
        try {
            log.debug("$TAG $METHOD_CALLED entity2Model()")
            log.debug("$PARAMS $entity")
            with(entity) {
                val sdf = SimpleDateFormat("dd/MM/yyyy")
                val dateStart: String = sdf.format(date_start)
                val dateEnd: String = sdf.format(date_end)
                return VisitModel(
                        id = id,
                        date_start = dateStart,
                        date_end = dateEnd,
                        description = description,
                        userId = user.id!!,
                        replicaId = replica.id!!
                )
            }

        } catch (ex: Exception) {
            log.error("$TAG entity2Model(): Exception -> $ex")
            return null
        }
    }

}
