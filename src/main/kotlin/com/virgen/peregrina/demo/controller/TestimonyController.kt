package com.virgen.peregrina.demo.controller

import com.virgen.peregrina.demo.data.model.TestimonyModel
import com.virgen.peregrina.demo.service.TestimonyService
import com.virgen.peregrina.demo.util.PARAMS
import com.virgen.peregrina.demo.util.base.BaseResponse
import com.virgen.peregrina.demo.util.base.BaseResult
import com.virgen.peregrina.demo.util.getLog
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/testimony")
class TestimonyController {

    @Autowired
    @Qualifier("testimonyService")
    private lateinit var testimonyService: TestimonyService

    companion object {
        private const val TAG = "TestimonyController ->"
    }

    private val log = getLog<TestimonyController>()

    @PostMapping("/create")
    fun createTestimony(
        @RequestBody model: TestimonyModel
    ): ResponseEntity<BaseResponse<TestimonyModel>> = try {
        log.info("$TAG createTestimony() $PARAMS $model")
        when (val result = testimonyService.create(model)) {
            is BaseResult.Success -> {
                ResponseEntity(
                    BaseResponse(
                        data = result.data
                    ), HttpStatus.OK
                )
            }

            is BaseResult.Error -> {
                ResponseEntity(
                    BaseResponse(
                        error = result.exception.toString()
                    ), HttpStatus.INTERNAL_SERVER_ERROR
                )
            }

            is BaseResult.NullOrEmptyData -> {
                ResponseEntity(BaseResponse(), HttpStatus.BAD_REQUEST)
            }
        }
    } catch (ex: Exception) {
        log.error("$TAG createTestimony(): Exception -> $ex")
        ResponseEntity(
            BaseResponse(
                error = ex.toString()
            ), HttpStatus.INTERNAL_SERVER_ERROR
        )
    }


//    fun getAll() {
//
//    }

    @GetMapping("/get-all/{replica_id}")
    fun getAll(
        @PathVariable replica_id: Long
    ) = try {
        when (val result = testimonyService.getAll(replica_id)) {
            is BaseResult.Success -> {
                ResponseEntity(
                    BaseResponse(data = result.data),
                    HttpStatus.OK
                )
            }

            is BaseResult.Error -> {
                ResponseEntity(
                    BaseResponse(
                        error = result.exception.toString(),
//                        message = result.exception.message
                    ), HttpStatus.OK
                )
            }

            is BaseResult.NullOrEmptyData -> {
                ResponseEntity(
                    BaseResponse(message = result.message),
                    HttpStatus.BAD_REQUEST
                )
            }
        }
    } catch (ex: Exception) {
        log.error("$TAG create(): Exception -> $ex")
        ResponseEntity(
            BaseResponse(
                error = ex.toString(),
//                message = ex.message
            ), HttpStatus.BAD_REQUEST
        )
    }


}