package com.virgen.peregrina.demo.service

import com.virgen.peregrina.demo.data.model.UserModel
import com.virgen.peregrina.demo.util.Service
import com.virgen.peregrina.demo.util.base.BaseResultService

interface UserService : Service<UserModel> {

    fun signIn(firebaseUid:String):BaseResultService<UserModel>
}