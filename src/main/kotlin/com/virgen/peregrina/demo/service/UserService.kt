package com.virgen.peregrina.demo.service

import com.virgen.peregrina.demo.data.model.UserModel
import com.virgen.peregrina.demo.util.component.Service
import com.virgen.peregrina.demo.util.base.BaseResult

interface UserService : Service<UserModel> {

    fun signIn(firebaseUid:String):BaseResult<UserModel>
}