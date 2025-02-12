package com.virgen.peregrina.demo.controller

import com.virgen.peregrina.demo.controller.util.ControllerHelper
import com.virgen.peregrina.demo.data.model.LoginModel
import com.virgen.peregrina.demo.data.request.LoginRequest
import com.virgen.peregrina.demo.service.auth.AuthService
import com.virgen.peregrina.demo.util.base.BaseApiResponse
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/auth")
class AuthController {

    companion object {
        private const val TAG = "AuthController ->"
    }

    @Autowired
    @Qualifier("authService")
    private lateinit var authService: AuthService

    @PostMapping("/login")
    fun login(@RequestBody body: LoginRequest): ResponseEntity<BaseApiResponse<LoginModel>> {
        val responseService = authService.login(body)
        return ControllerHelper.response(responseService)
    }
}