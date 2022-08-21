package com.virgen.peregrina.demo.controller

import com.virgen.peregrina.demo.data.model.UserModel
import com.virgen.peregrina.demo.service.UserService
import com.virgen.peregrina.demo.util.METHOD_CALLED
import com.virgen.peregrina.demo.util.PARAMS
import com.virgen.peregrina.demo.util.base.BaseResponse
import com.virgen.peregrina.demo.util.base.BaseResult
import org.apache.commons.logging.LogFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/user")
class UserController {

    companion object {
        private const val TAG = "UserController ->"
    }

    private val log = LogFactory.getLog(UserController::class.java)

    @Autowired
    @Qualifier("userService")
    private lateinit var userService: UserService

    @PostMapping("/create")
    fun signUp(@RequestBody userModel: UserModel): ResponseEntity<BaseResponse<UserModel>> {
        log.info("$TAG $METHOD_CALLED signUp()")
        log.info("$PARAMS $userModel")
        val result = userService.create(userModel)
        return when (result) {
            is BaseResult.Success -> {
                ResponseEntity(BaseResponse(result.data), HttpStatus.OK)
            }

            is BaseResult.Error -> {
                ResponseEntity(BaseResponse(
                        error = result.exception,
                        message = result.exception.message
                ), HttpStatus.OK)
            }

            is BaseResult.NullOrEmptyData -> {
                ResponseEntity(BaseResponse(), HttpStatus.BAD_REQUEST)
            }
        }
    }

    @GetMapping("/login")
    fun signIn(@PathVariable firebaseUid: String): ResponseEntity<BaseResponse<UserModel?>> {
        log.info("$TAG $METHOD_CALLED signIn()")
        log.info("$PARAMS firebaseId=$firebaseUid")
        val result = userService.signIn(firebaseUid)
        return when (result) {
            is BaseResult.Success -> {
                ResponseEntity(BaseResponse(result.data), HttpStatus.OK)
            }

            is BaseResult.NullOrEmptyData -> {
                ResponseEntity(BaseResponse(message = "User didn't find with firebaseUid = $firebaseUid"), HttpStatus.BAD_REQUEST)
            }

            is BaseResult.Error -> {
                ResponseEntity(BaseResponse(error = result.exception), HttpStatus.INTERNAL_SERVER_ERROR)
            }
        }
    }

}