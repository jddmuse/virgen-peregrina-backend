package com.virgen.peregrina.demo.service.replica

import com.virgen.peregrina.demo.data.model.ReplicaModel
import com.virgen.peregrina.demo.data.request.CreateReplicaRequest
import com.virgen.peregrina.demo.util.base.BaseServiceResponse
import com.virgen.peregrina.demo.util.component.Service

interface ReplicaService : Service<ReplicaModel> {

    fun create(request: CreateReplicaRequest): BaseServiceResponse<ReplicaModel> {
        return BaseServiceResponse.NullOrEmptyData()
    }


}