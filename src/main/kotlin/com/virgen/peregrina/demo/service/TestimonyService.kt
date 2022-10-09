package com.virgen.peregrina.demo.service

import com.virgen.peregrina.demo.data.model.TestimonyModel
import com.virgen.peregrina.demo.util.base.BaseResult
import com.virgen.peregrina.demo.util.component.Service

interface TestimonyService : Service<TestimonyModel> {


    fun getAll(replica_id:Long):BaseResult<List<TestimonyModel>>

}