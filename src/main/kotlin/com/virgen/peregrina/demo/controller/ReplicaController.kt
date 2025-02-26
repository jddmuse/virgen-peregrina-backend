package com.virgen.peregrina.demo.controller

import com.virgen.peregrina.demo.controller.util.ControllerHelper
import com.virgen.peregrina.demo.data.model.replica.ReplicaModel
import com.virgen.peregrina.demo.data.request.CreateReplicaRequest
import com.virgen.peregrina.demo.service.replica.ReplicaService
import com.virgen.peregrina.demo.util.base.BaseApiResponse
import org.apache.commons.logging.LogFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/replica")
class ReplicaController {

    companion object {
        private const val TAG = "ReplicaController ->"
    }

    private val log = LogFactory.getLog(ReplicaController::class.java)

    @Autowired
    @Qualifier("replicaService")
    private lateinit var replicaService: ReplicaService


    @GetMapping("/list")
    fun findAll(pageable: Pageable): ResponseEntity<BaseApiResponse<Page<ReplicaModel>>> {
        val responseService = replicaService.findAll(pageable)
        return ControllerHelper.response(responseService)
    }

    @PostMapping("/create")
    fun create(@RequestBody body: CreateReplicaRequest): ResponseEntity<BaseApiResponse<ReplicaModel>> {
        val responseService = replicaService.create(body)
        return ControllerHelper.response(responseService)
    }


}