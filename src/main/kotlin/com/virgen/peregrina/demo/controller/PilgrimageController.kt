package com.virgen.peregrina.demo.controller

import com.virgen.peregrina.demo.data.model.PilgrimageModel
import com.virgen.peregrina.demo.service.PilgrimageService
import com.virgen.peregrina.demo.util.METHOD_CALLED
import com.virgen.peregrina.demo.util.PARAMS
import com.virgen.peregrina.demo.util.PILGRIMAGE_SERVICE_NAME
import com.virgen.peregrina.demo.util.base.BaseResponse
import com.virgen.peregrina.demo.util.base.BaseResult
import org.apache.commons.logging.LogFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/pilgrimage")
class PilgrimageController {

    companion object {
        private const val TAG = "PilgrimageController ->"
    }

    private val log = LogFactory.getLog(PilgrimageController::class.java)

    @Autowired
    @Qualifier(PILGRIMAGE_SERVICE_NAME)
    private lateinit var pilgrimageService: PilgrimageService

    @PostMapping("/create")
    fun create(@RequestBody pilgrimageModel: PilgrimageModel): ResponseEntity<BaseResponse<PilgrimageModel>> = try {
        log.info("$TAG $METHOD_CALLED create()")
        log.info("$PARAMS $pilgrimageModel")
        when (val result = pilgrimageService.create(pilgrimageModel)) {
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
    fun getAll(): ResponseEntity<BaseResponse<List<PilgrimageModel>>> = try {
        log.info("$TAG $METHOD_CALLED getAll()")
        when (val result = pilgrimageService.getAll()) {
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

    @GetMapping("/get-all-limit/{limit}")
    fun getAllWithLimit(
        @PathVariable limit: Int = 30
    ): ResponseEntity<BaseResponse<List<PilgrimageModel>>> = try {
        log.info("$TAG $METHOD_CALLED getAllWithLimit()")
        when (val result = pilgrimageService.getAllWithLimit(limit)) {
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