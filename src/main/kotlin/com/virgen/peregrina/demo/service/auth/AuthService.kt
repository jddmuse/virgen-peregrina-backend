package com.virgen.peregrina.demo.service.auth

import com.virgen.peregrina.demo.data.model.LoginModel
import com.virgen.peregrina.demo.data.request.LoginRequest
import com.virgen.peregrina.demo.util.base.BaseServiceResponse

interface AuthService {

    fun login(request: LoginRequest): BaseServiceResponse<LoginModel>{
        return BaseServiceResponse.NullOrEmptyData()
    }
    fun logout(){}
}