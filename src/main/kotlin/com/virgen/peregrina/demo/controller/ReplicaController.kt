package com.virgen.peregrina.demo.controller

import com.virgen.peregrina.demo.data.entity.Replica
import com.virgen.peregrina.demo.data.model.ReplicaModel
import com.virgen.peregrina.demo.service.ReplicaService
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
@RequestMapping("/replica")
class ReplicaController {

    companion object {
        private const val TAG = "ReplicaController ->"
    }

    private val log = LogFactory.getLog(ReplicaController::class.java)

    @Autowired
    @Qualifier("replicaService")
    private lateinit var replicaService: ReplicaService

    @PostMapping("/create")
    fun createReplica(@RequestBody model: ReplicaModel): ResponseEntity<BaseResponse<ReplicaModel>> = try {
        log.info("$TAG $METHOD_CALLED createReplica()")
        log.info("$PARAMS $model")
        val result = replicaService.create(model)
        when (result) {
            is BaseResult.Success -> {
                ResponseEntity(BaseResponse(result.data), HttpStatus.OK)
            }

            is BaseResult.Error -> {
                ResponseEntity(BaseResponse(
                        error = result.exception,
//                        message = result.exception.message
                ), HttpStatus.INTERNAL_SERVER_ERROR)
            }

            is BaseResult.NullOrEmptyData -> {
                ResponseEntity(BaseResponse(), HttpStatus.BAD_REQUEST)
            }
        }
    } catch (ex: Exception) {
        log.error("$TAG createReplica(): Exception -> $ex")
        ResponseEntity(BaseResponse(
                error = ex,
//                message = ex.message
        ), HttpStatus.BAD_REQUEST)
    }

    @GetMapping("/get-all")
    fun getAll(): ResponseEntity<BaseResponse<List<ReplicaModel>>> = try {
        log.debug("$TAG, $METHOD_CALLED getAll()")
        when (val result = replicaService.getAll()) {
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
                ResponseEntity(BaseResponse(message = result.message), HttpStatus.BAD_REQUEST)
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