package com.virgen.peregrina.demo.service.implement

import com.virgen.peregrina.demo.data.entity.Visit
import com.virgen.peregrina.demo.data.entity.toEntity
import com.virgen.peregrina.demo.data.model.VisitModel
import com.virgen.peregrina.demo.data.model.toModel
import com.virgen.peregrina.demo.repository.VisitRepository
import com.virgen.peregrina.demo.service.VisitService
import com.virgen.peregrina.demo.util.METHOD_CALLED
import com.virgen.peregrina.demo.util.PARAMS
import com.virgen.peregrina.demo.util.base.BaseResultService
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

    override fun create(model: VisitModel): BaseResultService<VisitModel> = try {
        log.debug("$TAG $METHOD_CALLED create()")
        log.debug("$PARAMS $model")
        val newVisit = model.toEntity()
        val result = visitRepository.getAllAfterToday()
        if (result.isPresent) {
            val list: List<Visit> = result.get()
            var onRange = false
            for (i in list) {
                onRange = i.onRange(newVisit)
                if (onRange)
                    break
            }
            if (!onRange) {
                val result = visitRepository.save(newVisit)
                BaseResultService.Success(result.toModel()) // return
            }
        }
        BaseResultService.NullOrEmptyData() // return
    } catch (ex: Exception) {
        log.error("$TAG create(): Exception -> $ex")
        BaseResultService.Error(ex) // return
    }

    override fun delete(model: VisitModel): BaseResultService<Boolean> = try {
        log.debug("$TAG $METHOD_CALLED delete()")
        log.debug("$PARAMS $model")
        visitRepository.delete(model.toEntity())
        BaseResultService.Success(true) // return
    } catch (ex: Exception) {
        log.error("$TAG create(): Exception -> $ex")
        BaseResultService.Error(ex) // return
    }

    override fun get(model: VisitModel): BaseResultService<VisitModel> {
        // pending for implementing
        return BaseResultService.NullOrEmptyData()
    }

    override fun getAll(): BaseResultService<List<VisitModel>> = try {
        val result = visitRepository.findAll()
        val data = result.map { it.toModel() }
        BaseResultService.Success(data) // return
    } catch (ex: Exception) {
        log.error("$TAG create(): Exception -> $ex")
        BaseResultService.Error(ex) // return
    }

}