package com.virgen.peregrina.demo.service.implement

import com.virgen.peregrina.demo.data.converter.VisitConverter
import com.virgen.peregrina.demo.data.entity.Visit
import com.virgen.peregrina.demo.data.entity.toEntity
import com.virgen.peregrina.demo.data.model.VisitModel
import com.virgen.peregrina.demo.data.model.toModel
import com.virgen.peregrina.demo.repository.VisitRepository
import com.virgen.peregrina.demo.service.VisitService
import com.virgen.peregrina.demo.util.METHOD_CALLED
import com.virgen.peregrina.demo.util.PARAMS
import com.virgen.peregrina.demo.util.base.BaseResult
import org.apache.commons.logging.LogFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Service
import java.lang.Exception

@Service("visitService")
class VisitServiceImpl : VisitService {

    companion object {
        private const val TAG = "VisitService ->"
    }

    private val log = LogFactory.getLog(VisitServiceImpl::class.java)

    @Autowired
    @Qualifier("visitRepository")
    private lateinit var visitRepository: VisitRepository

    @Autowired
    @Qualifier("visitConverter")
    private lateinit var visitConverter: VisitConverter

    override fun create(model: VisitModel): BaseResult<VisitModel> = try {
        log.debug("$TAG $METHOD_CALLED create()")
        log.debug("$PARAMS $model")
        // casting model to entity
        var newVisit = visitConverter.toEntity(model)
        // getting all visits after today
        val result = visitRepository.getAllAfterToday()

        if (result.isPresent && newVisit != null) {
            val list: List<Visit> = result.get()
            var onRange = false
            // for each for visits
            for (i in list) {
                // if some newVisit date is on range, newVisit can't save
                onRange = i.onRange(newVisit)
                if (onRange)
                    break
            }
            // if it isn't on range, let's go to save !!
            if (!onRange) {
                newVisit = visitRepository.save(newVisit)
                BaseResult.Success(visitConverter.toModel(newVisit)) // return
            }
        }
        BaseResult.NullOrEmptyData() // return
    } catch (ex: Exception) {
        log.error("$TAG create(): Exception -> $ex")
        BaseResult.Error(ex) // return
    }

    override fun delete(model: VisitModel): BaseResult<Boolean> = try {
        log.debug("$TAG $METHOD_CALLED delete()")
        log.debug("$PARAMS $model")
        val entity = visitConverter.toEntity(model)
        if (entity?.id != null) {
            visitRepository.delete(entity)
            BaseResult.Success(true) // return
        }
        BaseResult.NullOrEmptyData()
    } catch (ex: Exception) {
        log.error("$TAG create(): Exception -> $ex")
        BaseResult.Error(ex) // return
    }

    override fun get(model: VisitModel): BaseResult<VisitModel> {
        // pending for implementing
        return BaseResult.NullOrEmptyData()
    }

    override fun getAll(): BaseResult<List<VisitModel>> = try {
        val result: MutableList<Visit> = visitRepository.findAll()
        val data = result.map { visitConverter.toModel(it)!! }
        BaseResult.Success(data) // return
    } catch (ex: Exception) {
        log.error("$TAG create(): Exception -> $ex")
        BaseResult.Error(ex) // return
    }

}