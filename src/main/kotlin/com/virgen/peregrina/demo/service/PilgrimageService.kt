package com.virgen.peregrina.demo.service

import com.virgen.peregrina.demo.data.model.PilgrimageModel
import com.virgen.peregrina.demo.util.base.BaseResult
import com.virgen.peregrina.demo.util.component.Service

interface PilgrimageService : Service<PilgrimageModel> {
    fun getAllByUserId(user_id: Long): BaseResult<List<PilgrimageModel>>
    fun getAllWithLimit(limit: Int): BaseResult<List<PilgrimageModel>>
}