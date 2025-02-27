package com.virgen.peregrina.demo.service.replica

import com.virgen.peregrina.demo.data.converter.ReplicaConverter
import com.virgen.peregrina.demo.data.entity.Replica
import com.virgen.peregrina.demo.data.model.replica.ReplicaModel
import com.virgen.peregrina.demo.data.model.replica.model
import com.virgen.peregrina.demo.data.request.CreateReplicaRequest
import com.virgen.peregrina.demo.repository.PilgrimageRepository
import com.virgen.peregrina.demo.repository.ReplicaRepository
import com.virgen.peregrina.demo.repository.UserRepository
import com.virgen.peregrina.demo.util.base.BaseServiceResponse
import com.virgen.peregrina.demo.util.getLog
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service


@Service("replicaService")
class ReplicaServiceImpl : ReplicaService {

    companion object {
        private const val TAG = "ReplicaServiceImpl ->"
    }

    @Autowired
    @Qualifier("replicaRepository")
    private lateinit var replicaRepository: ReplicaRepository

    @Autowired
    @Qualifier("userRepository")
    private lateinit var userRepository: UserRepository

    @Autowired
    @Qualifier("pilgrimageRepository")
    private lateinit var pilgrimageRepository: PilgrimageRepository

    @Autowired
    @Qualifier("replicaConverter")
    private lateinit var replicaConverter: ReplicaConverter

    private val log = getLog<ReplicaServiceImpl>()

    override fun create(request: CreateReplicaRequest): BaseServiceResponse<ReplicaModel> {
        try {
            val userEntity = userRepository.findById(request.ownerId)
            if(!userEntity.isPresent)
                return BaseServiceResponse.NullOrEmptyData("El usuario no existe")
            val replicaData2Save = Replica(
                photoUrl = null,
                code = request.code,
                birthdate = request.birthdate,
                user = userEntity.get()
            )
            val replicaEntity = replicaRepository.save(replicaData2Save)
            return BaseServiceResponse.Success(replicaEntity.model())
        } catch (ex:Exception) {
            log.error("$TAG create(): Exception -> $ex")
            return BaseServiceResponse.Error(ex, "No se pudo crear la replica")
        }
    }

    override fun findAll(pageable: Pageable): BaseServiceResponse<Page<ReplicaModel>> {
        return try {
            val result = replicaRepository.findAll(pageable)
                .map { it.model() }
            BaseServiceResponse.Success(result)
        } catch (ex:Exception) {
            log.error("$TAG findAll(): Exception -> $ex")
            BaseServiceResponse.Error(ex)
        }
    }

//    override fun create(model: ReplicaModel): BaseServiceResponse<ReplicaModel> = try {
//        log.info("$TAG $METHOD_CALLED create() $PARAMS $model")
//        val newReplica = replicaRepository.save(replicaConverter.toEntity(model).get())
//        BaseServiceResponse.Success(replicaConverter.toModel(newReplica).get())
//    } catch (ex: Exception) {
//        log.error("$TAG create(): Exception -> $ex")
//        BaseServiceResponse.Error(ex) // return
//    }
//
//    override fun delete(model: ReplicaModel): BaseServiceResponse<Boolean> {
//        TODO("Not yet implemented")
//    }
//
//    override fun get(id: Long): BaseServiceResponse<ReplicaModel> {
//        TODO("Not yet implemented")
//    }
//
//    override fun getAll(): BaseServiceResponse<List<ReplicaModel>> = try {
//        val result: MutableList<Replica> = replicaRepository.findAll()
//        val data = result.map { replicaConverter.toModel(it).get() }
//        BaseServiceResponse.Success(data) // return
//    } catch (ex: Exception) {
//        log.error("$TAG getAll(): Exception -> $ex")
//        BaseServiceResponse.Error(ex) // return
//    }

}