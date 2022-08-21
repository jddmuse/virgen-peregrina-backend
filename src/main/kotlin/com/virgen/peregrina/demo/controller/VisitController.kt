package com.virgen.peregrina.demo.controller

import com.virgen.peregrina.demo.data.model.VisitModel
import com.virgen.peregrina.demo.service.VisitService
import com.virgen.peregrina.demo.util.METHOD_CALLED
import com.virgen.peregrina.demo.util.PARAMS
import com.virgen.peregrina.demo.util.base.BaseResponse
import com.virgen.peregrina.demo.util.base.BaseResult
import org.apache.commons.logging.LogFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/visit")
class VisitController {

    companion object {
        private const val TAG = "VisitController ->"
    }

    private val log = LogFactory.getLog(VisitController::class.java)

    @Autowired
    @Qualifier("visitService")
    private lateinit var visitService: VisitService

    @PostMapping("/create")
    fun createVisit(@RequestBody visitModel: VisitModel): ResponseEntity<BaseResponse<VisitModel>> = try {
        log.debug("$TAG $METHOD_CALLED signUp()")
        log.debug("$PARAMS $visitModel")
        val result = visitService.create(visitModel)
        when (result) {
            is BaseResult.Success -> {
                ResponseEntity(BaseResponse(result.data), HttpStatus.OK)
            }

            is BaseResult.Error -> {
                ResponseEntity(BaseResponse(
                        error = result.exception,
//                        message = result.exception.message
                ), HttpStatus.OK)
            }

            is BaseResult.NullOrEmptyData -> {
                ResponseEntity(BaseResponse(), HttpStatus.BAD_REQUEST)
            }
        }
    } catch (ex: Exception) {
        log.error("$TAG create(): Exception -> $ex")
        ResponseEntity(BaseResponse(
                error = ex,
//                message = ex.message
        ), HttpStatus.BAD_REQUEST)
    }

    @GetMapping("/login/get-all")
    fun getVisits(): ResponseEntity<BaseResponse<List<VisitModel>>> = try {
        log.debug("$TAG $METHOD_CALLED getVisits()")
        val result: BaseResult<List<VisitModel>> = visitService.getAll()
        when (result) {
            is BaseResult.Success -> {
                ResponseEntity(BaseResponse(result.data), HttpStatus.OK)
            }

            is BaseResult.Error -> {
                ResponseEntity(BaseResponse(
                        error = result.exception,
                        message = result.exception.message
                ), HttpStatus.BAD_REQUEST)
            }

            is BaseResult.NullOrEmptyData -> {
                ResponseEntity(BaseResponse(), HttpStatus.BAD_REQUEST)
            }
        }
    } catch (ex: Exception) {
        log.error("$TAG create(): Exception -> $ex")
        ResponseEntity(BaseResponse(
                error = ex,
//                message = ex.message
        ), HttpStatus.BAD_REQUEST)
    }

}