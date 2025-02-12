package com.virgen.peregrina.demo.controller

import com.virgen.peregrina.demo.controller.util.ControllerHelper
import com.virgen.peregrina.demo.data.model.UserModel
import com.virgen.peregrina.demo.data.request.CreateUserRequest
import com.virgen.peregrina.demo.data.request.GetUserRequest
import com.virgen.peregrina.demo.data.request.toModel
import com.virgen.peregrina.demo.data.response.GetUserResponse
import com.virgen.peregrina.demo.service.user.UserService
import com.virgen.peregrina.demo.util.base.BaseApiResponse
import com.virgen.peregrina.demo.util.base.BaseServiceResponse
import org.apache.commons.logging.LogFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
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

    @GetMapping("/get")
    fun get(body: GetUserRequest): BaseApiResponse<GetUserResponse> {
        return BaseApiResponse()
    }

    @GetMapping("/getAll")
    fun getAll(): BaseApiResponse<List<GetUserResponse>> {
        return BaseApiResponse()
    }

    @PostMapping("/create")
    fun create(@RequestBody body: CreateUserRequest): ResponseEntity<BaseApiResponse<UserModel>> {
        val serviceResponse: BaseServiceResponse<UserModel> = userService.create(body.toModel())
        return ControllerHelper.response(serviceResponse)
    }

    @GetMapping("/update")
    fun update(body: CreateUserRequest): BaseApiResponse<UserModel> {
        return BaseApiResponse()
    }


}