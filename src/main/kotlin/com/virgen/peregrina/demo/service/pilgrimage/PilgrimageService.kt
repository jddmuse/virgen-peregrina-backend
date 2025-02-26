package com.virgen.peregrina.demo.service.pilgrimage

import com.virgen.peregrina.demo.data.model.pilgrimage.PilgrimageModel
import com.virgen.peregrina.demo.util.base.BaseServiceResponse
import com.virgen.peregrina.demo.util.component.Service
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable


interface PilgrimageService : Service<PilgrimageModel> {
//    fun getAllByUserId(user_id: Long): BaseServiceResponse<List<PilgrimageModel>>
//    fun getAllWithLimit(limit: Int): BaseServiceResponse<List<PilgrimageModel>>

    fun findAll(pageable: Pageable): BaseServiceResponse<Page<PilgrimageModel>>

}