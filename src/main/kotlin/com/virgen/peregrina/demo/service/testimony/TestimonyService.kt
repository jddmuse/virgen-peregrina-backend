package com.virgen.peregrina.demo.service.testimony

import com.virgen.peregrina.demo.data.model.TestimonyModel
import com.virgen.peregrina.demo.data.request.CreateTestimonyRequest
import com.virgen.peregrina.demo.util.base.BaseServiceResponse
import com.virgen.peregrina.demo.util.component.Service

interface TestimonyService : Service<TestimonyModel> {

    fun create(model: CreateTestimonyRequest): BaseServiceResponse<TestimonyModel>
//    fun getAll(replica_id:Long):BaseServiceResponse<List<TestimonyModel>>

}