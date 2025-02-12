package com.virgen.peregrina.demo.service.replica

import com.virgen.peregrina.demo.data.model.ReplicaModel
import com.virgen.peregrina.demo.data.request.CreateReplicaRequest
import com.virgen.peregrina.demo.util.base.BaseServiceResponse
import com.virgen.peregrina.demo.util.component.Service
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable


interface ReplicaService : Service<ReplicaModel> {

    fun create(request: CreateReplicaRequest): BaseServiceResponse<ReplicaModel> {
        return BaseServiceResponse.NullOrEmptyData()
    }

    fun findAll(pageable: Pageable): BaseServiceResponse<Page<ReplicaModel>> {
        return BaseServiceResponse.NullOrEmptyData()
    }


}