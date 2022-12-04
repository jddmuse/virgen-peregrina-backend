package com.virgen.peregrina.demo.service

import com.virgen.peregrina.demo.data.model.UserModel
import com.virgen.peregrina.demo.util.component.Service
import com.virgen.peregrina.demo.util.base.BaseResult

interface UserService : Service<UserModel> {
    fun login(firebaseUid: String): BaseResult<UserModel>
    fun getAllPilgrims(): BaseResult<List<UserModel>>

    fun update(model: UserModel): BaseResult<UserModel>
}