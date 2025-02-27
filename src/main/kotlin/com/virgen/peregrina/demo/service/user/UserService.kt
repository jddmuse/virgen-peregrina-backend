package com.virgen.peregrina.demo.service.user

import com.virgen.peregrina.demo.data.model.user.UserModel
import com.virgen.peregrina.demo.data.request.CreateUserRequest
import com.virgen.peregrina.demo.util.base.BaseServiceResponse
import com.virgen.peregrina.demo.util.component.Service

interface UserService : Service<UserModel> {
    fun create(request: CreateUserRequest): BaseServiceResponse<UserModel>
    fun get(email: String, pass: String):BaseServiceResponse<UserModel> {
        return BaseServiceResponse.NullOrEmptyData()
    }
}