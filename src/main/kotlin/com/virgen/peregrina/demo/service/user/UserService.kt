package com.virgen.peregrina.demo.service.user

import com.virgen.peregrina.demo.data.model.UserModel
import com.virgen.peregrina.demo.util.base.BaseServiceResponse
import com.virgen.peregrina.demo.util.component.Service

interface UserService : Service<UserModel> {
    fun get(email: String, pass: String):BaseServiceResponse<UserModel> {
        return BaseServiceResponse.NullOrEmptyData()
    }
}