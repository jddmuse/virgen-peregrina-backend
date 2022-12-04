package com.virgen.peregrina.demo.controller

import com.virgen.peregrina.demo.data.model.UserModel
import com.virgen.peregrina.demo.data.request.LoginRequest
import com.virgen.peregrina.demo.service.UserService
import com.virgen.peregrina.demo.util.METHOD_CALLED
import com.virgen.peregrina.demo.util.PARAMS
import com.virgen.peregrina.demo.util.base.BaseResponse
import com.virgen.peregrina.demo.util.base.BaseResult
import org.apache.catalina.User
import org.apache.commons.logging.LogFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/user")
class UserController {

    companion object {
        private const val TAG = "[UserController] ->"
    }

    private val log = LogFactory.getLog(UserController::class.java)

    @Autowired
    @Qualifier("userService")
    private lateinit var userService: UserService

    @GetMapping("/get-all-pilgrims")
    fun getAllPilgrims(): ResponseEntity<BaseResponse<List<UserModel>>> {
        log.info("$TAG $METHOD_CALLED getAllPilgrims()")
        return when (val result = userService.getAllPilgrims()) {
            is BaseResult.Success -> {
                ResponseEntity(
                    BaseResponse(result.data),
                    HttpStatus.OK
                )
            }

            is BaseResult.Error -> {
                ResponseEntity(
                    BaseResponse(
                        error = result.exception.toString(),
                        message = "An internal error has occurred"
                    ),
                    HttpStatus.INTERNAL_SERVER_ERROR
                )
            }

            is BaseResult.NullOrEmptyData -> {
                ResponseEntity(
                    BaseResponse(
                        message = "An internal error has occurred, try it again"
                    ),
                    HttpStatus.BAD_REQUEST
                )
            }
        }
    }

    @PostMapping("/create")
    fun create(@RequestBody userModel: UserModel): ResponseEntity<BaseResponse<UserModel>> {
        log.info("$TAG $METHOD_CALLED create()")
        log.info("$PARAMS $userModel")
        return when (val result = userService.create(userModel)) {
            is BaseResult.Success -> {
                ResponseEntity(
                    BaseResponse(result.data),
                    HttpStatus.OK
                )
            }

            is BaseResult.Error -> {
                ResponseEntity(
                    BaseResponse(
                        error = result.exception.toString(),
                        message = "An internal error has occurred"
                    ),
                    HttpStatus.INTERNAL_SERVER_ERROR
                )
            }

            is BaseResult.NullOrEmptyData -> {
                ResponseEntity(
                    BaseResponse(
                        message = "An internal error has occurred, try it again"
                    ),
                    HttpStatus.BAD_REQUEST
                )
            }
        }
    }

    @PostMapping("/login")
    fun login(@RequestBody loginRequest: LoginRequest): ResponseEntity<BaseResponse<UserModel?>> {
        log.info("$TAG $METHOD_CALLED login()")
        log.info("$PARAMS loginRequest=$loginRequest")
        return when (val result = userService.login(loginRequest.uuid)) {
            is BaseResult.Success -> {
                ResponseEntity(
                    BaseResponse(result.data),
                    HttpStatus.OK
                )
            }

            is BaseResult.NullOrEmptyData -> {
                ResponseEntity(
                    BaseResponse(message = "It could not find an user with UUID = ${loginRequest.uuid}"),
                    HttpStatus.BAD_REQUEST
                )
            }

            is BaseResult.Error -> {
                ResponseEntity(
                    BaseResponse(
                        error = result.exception.toString(),
                        message = "An internal error has occurred"
                    ),
                    HttpStatus.INTERNAL_SERVER_ERROR
                )
            }
        }
    }

    @GetMapping("/get-all")
    fun getAll(): ResponseEntity<BaseResponse<List<UserModel>>> = try {
        log.info("$TAG $METHOD_CALLED getAll()")
        when (val result = userService.getAll()) {
            is BaseResult.Success -> {
                ResponseEntity(BaseResponse(result.data), HttpStatus.OK)
            }

            is BaseResult.Error -> {
                ResponseEntity(
                    BaseResponse(
                        error = result.exception.toString(),
                        message = "An internal error has occurred"
                    ), HttpStatus.BAD_REQUEST
                )
            }

            is BaseResult.NullOrEmptyData -> {
                ResponseEntity(BaseResponse(), HttpStatus.BAD_REQUEST)
            }
        }
    } catch (ex: Exception) {
        log.error("$TAG create(): Exception -> $ex")
        ResponseEntity(
            BaseResponse(
                error = ex.toString(),
                message = "An internal error has occurred"
            ),
            HttpStatus.BAD_REQUEST
        )
    }

    @PostMapping("/update")
    fun update(@RequestBody userModel: UserModel): ResponseEntity<BaseResponse<UserModel>> {
        log.info("$TAG $METHOD_CALLED update()")
        log.info("$PARAMS $userModel")
        return when (val result = userService.update(userModel)) {
            is BaseResult.Success -> {
                ResponseEntity(
                    BaseResponse(result.data),
                    HttpStatus.OK
                )
            }

            is BaseResult.Error -> {
                ResponseEntity(
                    BaseResponse(
                        error = result.exception.toString(),
                        message = "An internal error has occurred"
                    ),
                    HttpStatus.INTERNAL_SERVER_ERROR
                )
            }

            is BaseResult.NullOrEmptyData -> {
                ResponseEntity(
                    BaseResponse(
                        message = "An internal error has occurred, try it again"
                    ),
                    HttpStatus.BAD_REQUEST
                )
            }
        }
    }
}