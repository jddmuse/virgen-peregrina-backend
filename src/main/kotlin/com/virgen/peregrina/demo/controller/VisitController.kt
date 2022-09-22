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
    fun create(@RequestBody visitModel: VisitModel): ResponseEntity<BaseResponse<VisitModel>> = try {
        log.info("$TAG $METHOD_CALLED create()")
        log.info("$PARAMS $visitModel")
        when (val result = visitService.create(visitModel)) {
            is BaseResult.Success -> {
                ResponseEntity(
                        BaseResponse(result.data), HttpStatus.OK
                )
            }

            is BaseResult.Error -> {
                ResponseEntity(
                        BaseResponse(error = result.exception.toString()), HttpStatus.OK
                )
            }

            is BaseResult.NullOrEmptyData -> {
                ResponseEntity(
                        BaseResponse(message = result.message), HttpStatus.BAD_REQUEST
                )
            }
        }
    } catch (ex: Exception) {
        log.error("$TAG create(): Exception -> $ex")
        ResponseEntity(
                BaseResponse(error = ex.toString()), HttpStatus.BAD_REQUEST
        )
    }

    @GetMapping("/get-all")
    fun getAll(): ResponseEntity<BaseResponse<List<VisitModel>>> = try {
        log.info("$TAG $METHOD_CALLED getAll()")
        when (val result = visitService.getAll()) {
            is BaseResult.Success -> {
                ResponseEntity(
                        BaseResponse(result.data), HttpStatus.OK
                )
            }

            is BaseResult.Error -> {
                ResponseEntity(
                        BaseResponse(error = result.exception.toString(), message = result.exception.message),
                        HttpStatus.BAD_REQUEST
                )
            }

            is BaseResult.NullOrEmptyData -> {
                ResponseEntity(
                        BaseResponse(), HttpStatus.BAD_REQUEST
                )
            }
        }
    } catch (ex: Exception) {
        log.error("$TAG create(): Exception -> $ex")
        ResponseEntity(
                BaseResponse(error = ex.toString()), HttpStatus.BAD_REQUEST
        )
    }

}