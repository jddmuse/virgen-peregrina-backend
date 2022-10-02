package com.virgen.peregrina.demo.service.implement

import com.virgen.peregrina.demo.data.converter.ReplicaConverter
import com.virgen.peregrina.demo.data.entity.Replica
import com.virgen.peregrina.demo.data.model.ReplicaModel
import com.virgen.peregrina.demo.repository.ReplicaRepository
import com.virgen.peregrina.demo.service.ReplicaService
import com.virgen.peregrina.demo.util.METHOD_CALLED
import com.virgen.peregrina.demo.util.PARAMS
import com.virgen.peregrina.demo.util.base.BaseResult
import com.virgen.peregrina.demo.util.getLog
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
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
    @Qualifier("replicaConverter")
    private lateinit var replicaConverter: ReplicaConverter

    private val log = getLog<ReplicaServiceImpl>()

    override fun create(model: ReplicaModel): BaseResult<ReplicaModel> = try {
        log.info("$TAG $METHOD_CALLED create() $PARAMS $model")
        val newReplica = replicaRepository.save(replicaConverter.toEntity(model).get())
        BaseResult.Success(replicaConverter.toModel(newReplica).get())
    } catch (ex: Exception) {
        log.error("$TAG create(): Exception -> $ex")
        BaseResult.Error(ex) // return
    }

    override fun delete(model: ReplicaModel): BaseResult<Boolean> {
        TODO("Not yet implemented")
    }

    override fun get(id: Long): BaseResult<ReplicaModel> {
        TODO("Not yet implemented")
    }

    override fun getAll(): BaseResult<List<ReplicaModel>> = try {
        val result: MutableList<Replica> = replicaRepository.findAll()
        val data = result.map { replicaConverter.toModel(it).get() }
        BaseResult.Success(data) // return
    } catch (ex: Exception) {
        log.error("$TAG getAll(): Exception -> $ex")
        BaseResult.Error(ex) // return
    }

}