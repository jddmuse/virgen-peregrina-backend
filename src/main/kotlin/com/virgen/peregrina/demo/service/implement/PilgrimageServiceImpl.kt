package com.virgen.peregrina.demo.service.implement

import com.virgen.peregrina.demo.data.converter.PilgrimageConverter
import com.virgen.peregrina.demo.data.entity.Pilgrimage
import com.virgen.peregrina.demo.data.model.PilgrimageModel
import com.virgen.peregrina.demo.repository.PilgrimageRepository
import com.virgen.peregrina.demo.service.PilgrimageService
import com.virgen.peregrina.demo.util.*
import com.virgen.peregrina.demo.util.base.BaseResult
import org.apache.commons.logging.LogFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
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
    @Qualifier(PILGRIMAGE_CONVERTER_NAME)
    private lateinit var pilgrimageConverter: PilgrimageConverter


    override fun getAllByUserId(user_id: Long): BaseResult<List<PilgrimageModel>> {
        try {
            // pending
            return BaseResult.NullOrEmptyData()
        } catch (ex: Exception) {
            log.error("$TAG getAllByUserId(): Exception -> $ex")
            return BaseResult.Error(ex, "")
        }
    }

    override fun create(model: PilgrimageModel): BaseResult<PilgrimageModel> {
        try {
            log.info("$TAG $METHOD_CALLED create()")
            log.info("$PARAMS $model")
            // casting model to entity
            var newPilgrimage = pilgrimageConverter.toEntity(model).get()
            // getting all newPilgrimages after today
            val listOptional = pilgrimageRepository.getAllAfterToday()
            // adding when validations
            return when {
                // newPilgrimage is null
                newPilgrimage == null -> {
                    BaseResult.NullOrEmptyData(ERROR_PILGRIMAGE_ON_RANGE) // return
                }
                // date_start is greater than date_end
                newPilgrimage.date_start.after(newPilgrimage.date_end) -> {
                    BaseResult.NullOrEmptyData(ERROR_START_DATE_GREATER_THAN_END_DATE) // return
                }
                // else branch
                else -> {
                    val list: List<Pilgrimage> = listOptional.get()
                    var onRange = false
                    if (list.isNotEmpty()) {
                        // for each for newPilgrimages
                        for (i in list) {
                            // if some newPilgrimage date is on range, newVisit can't save
                            onRange = i.onRange(newPilgrimage!!) || newPilgrimage.onRange(i)
                            if (onRange)
                                break
                        }
                    }
                    // if it isn't on range, let's go to save !!
                    if (!onRange) {
                        newPilgrimage = pilgrimageRepository.save(newPilgrimage)
                        return BaseResult.Success(pilgrimageConverter.toModel(newPilgrimage).get()) // return
                    }
                    log.info("$TAG onRange = $onRange")
                    BaseResult.NullOrEmptyData(ERROR_PILGRIMAGE_ON_RANGE) // return
                }
            }


        } catch (ex: Exception) {
            log.error("$TAG create(): Exception -> $ex")
            return BaseResult.Error(ex, ERROR_PILGRIMAGE_ON_RANGE) // return
        }
    }

    override fun delete(model: PilgrimageModel): BaseResult<Boolean> = try {
        log.info("$TAG $METHOD_CALLED delete()")
        log.info("$PARAMS $model")
        val entity = pilgrimageConverter.toEntity(model).get()
        if (entity?.id != null) {
            pilgrimageRepository.delete(entity)
            BaseResult.Success(true) // return
        }
        BaseResult.NullOrEmptyData()
    } catch (ex: Exception) {
        log.error("$TAG create(): Exception -> $ex")
        BaseResult.Error(ex) // return
    }

    override fun get(id: Long): BaseResult<PilgrimageModel> {
        TODO("Not yet implemented")
    }


    override fun getAll(): BaseResult<List<PilgrimageModel>> = try {
        val result: MutableList<Pilgrimage> = pilgrimageRepository.findAll()
        val data = result.map { pilgrimageConverter.toModel(it).get() }
        BaseResult.Success(data) // return
    } catch (ex: Exception) {
        log.error("$TAG getAll(): Exception -> $ex")
        BaseResult.Error(ex) // return
    }

    override fun getAllWithLimit(limit: Int): BaseResult<List<PilgrimageModel>> = try {
        val result = pilgrimageRepository.findAllWithLimit(limit).get()
        val data = result.map { pilgrimageConverter.toModel(it).get() }
        BaseResult.Success(data) // return
    } catch (ex: Exception) {
        log.error("$TAG getAll(): Exception -> $ex")
        BaseResult.Error(ex) // return
    }


}