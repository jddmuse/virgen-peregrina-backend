package com.virgen.peregrina.demo.service.pilgrimage

import com.fasterxml.jackson.databind.ObjectMapper
import com.virgen.peregrina.demo.data.entity.EnumPilgrimageStatus
import com.virgen.peregrina.demo.data.entity.Pilgrimage
import com.virgen.peregrina.demo.data.entity.toModel
import com.virgen.peregrina.demo.data.model.PilgrimageModel
import com.virgen.peregrina.demo.repository.PilgrimageRepository
import com.virgen.peregrina.demo.repository.ReplicaRepository
import com.virgen.peregrina.demo.repository.UserRepository
import com.virgen.peregrina.demo.util.PILGRIMAGE_REPOSITORY_NAME
import com.virgen.peregrina.demo.util.PILGRIMAGE_SERVICE_NAME
import com.virgen.peregrina.demo.util.base.BaseRepositoryResponse
import com.virgen.peregrina.demo.util.base.BaseServiceResponse
import org.apache.commons.logging.LogFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service(PILGRIMAGE_SERVICE_NAME)
class PilgrimageServiceImpl : PilgrimageService {

    companion object {
        private const val TAG = "PilgrimageServiceImpl ->"
    }

    private val log = LogFactory.getLog(PilgrimageServiceImpl::class.java)

    @Autowired
    @Qualifier(PILGRIMAGE_REPOSITORY_NAME)
    private lateinit var pilgrimageRepository: PilgrimageRepository

    @Autowired
    @Qualifier("userRepository")
    private lateinit var userRepository: UserRepository

    @Autowired
    @Qualifier("replicaRepository")
    private lateinit var replicaRepository: ReplicaRepository

    @Autowired
    private lateinit var objectMapper: ObjectMapper

//    @Autowired
//    @Qualifier(PILGRIMAGE_CONVERTER_NAME)
//    private lateinit var pilgrimageConverter: PilgrimageConverter

    override fun findAll(pageable: Pageable): BaseServiceResponse<Page<PilgrimageModel>> {
        return try {
            val result = pilgrimageRepository.findAll(pageable).map { it.toModel() }
            BaseServiceResponse.Success(result)
        } catch (ex: Exception) {
            log.error("$TAG create(): Excepción -> $ex")
            BaseServiceResponse.Error(ex)
        }
    }

    override fun create(model: PilgrimageModel): BaseServiceResponse<PilgrimageModel> {
        try {
            val replicaEntity = replicaRepository.findById(model.replicaId)
            if(!replicaEntity.isPresent)
                return BaseServiceResponse.NullOrEmptyData()
            val userEntity = userRepository.findById(model.userId)
            if(!userEntity.isPresent)
                return BaseServiceResponse.NullOrEmptyData()
            val jsonStringParam = objectMapper.writeValueAsString(model)
            val jsonResponse = pilgrimageRepository.insert2(jsonStringParam)
            val response = objectMapper.readValue(jsonResponse, BaseRepositoryResponse::class.java)?.let {
                BaseRepositoryResponse(
                    data = it.data?.let { data: Any -> objectMapper.convertValue(data, PilgrimageModel::class.java) },
                    message = it.message,
                    success = it.success
                )
            }
            return if(response != null && response.success) {
                BaseServiceResponse.Success(response.data!!)
            } else
                BaseServiceResponse.NullOrEmptyData(response?.message)
        } catch (ex:Exception) {
            log.error("$TAG create(): Excepción -> $ex")
            return BaseServiceResponse.Error(ex)
        }
    }



//    override fun getAllByUserId(user_id: Long): BaseServiceResponse<List<PilgrimageModel>> {
//        try {
//            // pending
//            return BaseServiceResponse.NullOrEmptyData()
//        } catch (ex: Exception) {
//            log.error("$TAG getAllByUserId(): Exception -> $ex")
//            return BaseServiceResponse.Error(ex, "")
//        }
//    }
//
//    override fun create(model: PilgrimageModel): BaseServiceResponse<PilgrimageModel> {
//        try {
//            log.info("$TAG $METHOD_CALLED create()")
//            log.info("$PARAMS $model")
//            // casting model to entity
//            var newPilgrimage = pilgrimageConverter.toEntity(model).get()
//            // getting all newPilgrimages after today
//            val listOptional = pilgrimageRepository.getAllAfterToday()
//            // adding when validations
//            return when {
//                // newPilgrimage is null
//                newPilgrimage == null -> {
//                    BaseServiceResponse.NullOrEmptyData(ERROR_PILGRIMAGE_ON_RANGE) // return
//                }
//                // date_start is greater than date_end
//                newPilgrimage.startDate.after(newPilgrimage.endDate) -> {
//                    BaseServiceResponse.NullOrEmptyData(ERROR_START_DATE_GREATER_THAN_END_DATE) // return
//                }
//                // else branch
//                else -> {
//                    val list: List<Pilgrimage> = listOptional.get()
//                    var onRange = false
//                    if (list.isNotEmpty()) {
//                        // for each for newPilgrimages
//                        for (i in list) {
//                            // if some newPilgrimage date is on range, newVisit can't save
//                            onRange = i.onRange(newPilgrimage!!) || newPilgrimage.onRange(i)
//                            if (onRange)
//                                break
//                        }
//                    }
//                    // if it isn't on range, let's go to save !!
//                    if (!onRange) {
//                        newPilgrimage = pilgrimageRepository.save(newPilgrimage)
//                        return BaseServiceResponse.Success(pilgrimageConverter.toModel(newPilgrimage).get()) // return
//                    }
//                    log.info("$TAG onRange = $onRange")
//                    BaseServiceResponse.NullOrEmptyData(ERROR_PILGRIMAGE_ON_RANGE) // return
//                }
//            }
//
//
//        } catch (ex: Exception) {
//            log.error("$TAG create(): Exception -> $ex")
//            return BaseServiceResponse.Error(ex, ERROR_PILGRIMAGE_ON_RANGE) // return
//        }
//    }
//
//    override fun delete(model: PilgrimageModel): BaseServiceResponse<Boolean> = try {
//        log.info("$TAG $METHOD_CALLED delete()")
//        log.info("$PARAMS $model")
//        val entity = pilgrimageConverter.toEntity(model).get()
//        if (entity?.id != null) {
//            pilgrimageRepository.delete(entity)
//            BaseServiceResponse.Success(true) // return
//        }
//        BaseServiceResponse.NullOrEmptyData()
//    } catch (ex: Exception) {
//        log.error("$TAG create(): Exception -> $ex")
//        BaseServiceResponse.Error(ex) // return
//    }
//
//    override fun get(id: Long): BaseServiceResponse<PilgrimageModel> {
//        TODO("Not yet implemented")
//    }
//
//
//    override fun getAll(): BaseServiceResponse<List<PilgrimageModel>> = try {
//        val result: MutableList<Pilgrimage> = pilgrimageRepository.findAll()
//        val data = result.map { pilgrimageConverter.toModel(it).get() }
//        BaseServiceResponse.Success(data) // return
//    } catch (ex: Exception) {
//        log.error("$TAG getAll(): Exception -> $ex")
//        BaseServiceResponse.Error(ex) // return
//    }
//
//    override fun getAllWithLimit(limit: Int): BaseServiceResponse<List<PilgrimageModel>> = try {
//        val result = pilgrimageRepository.findAllWithLimit(limit).get()
//        val data = result.map { pilgrimageConverter.toModel(it).get() }
//        BaseServiceResponse.Success(data) // return
//    } catch (ex: Exception) {
//        log.error("$TAG getAll(): Exception -> $ex")
//        BaseServiceResponse.Error(ex) // return
//    }


}