package com.virgen.peregrina.demo.controller

import com.virgen.peregrina.demo.controller.util.ControllerHelper
import com.virgen.peregrina.demo.data.model.pilgrimage.PilgrimageModel
import com.virgen.peregrina.demo.data.request.CreatePilgrimageRequest
import com.virgen.peregrina.demo.data.request.GetPilgrimageRequest
import com.virgen.peregrina.demo.data.response.GetPilgrimageResponse
import com.virgen.peregrina.demo.service.pilgrimage.PilgrimageService
import com.virgen.peregrina.demo.util.PILGRIMAGE_SERVICE_NAME
import com.virgen.peregrina.demo.util.base.BaseApiResponse
import org.apache.commons.logging.LogFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/pilgrimage")
class PilgrimageController {

    companion object {
        private const val TAG = "PilgrimageController ->"
    }

    private val log = LogFactory.getLog(PilgrimageController::class.java)

    @Autowired
    @Qualifier(PILGRIMAGE_SERVICE_NAME)
    private lateinit var pilgrimageService: PilgrimageService

    @GetMapping("/get")
    fun get(body: GetPilgrimageRequest): BaseApiResponse<GetPilgrimageResponse> {
        return BaseApiResponse()
    }

    @GetMapping("/list")
    fun findAll(pageable: Pageable): ResponseEntity<BaseApiResponse<Page<PilgrimageModel>>> {
        val responseService = pilgrimageService.findAll(pageable)
        return ControllerHelper.response(responseService)
    }

    @PostMapping("/create")
    fun create(@RequestBody body: CreatePilgrimageRequest): ResponseEntity<BaseApiResponse<PilgrimageModel>> {
        val responseService = pilgrimageService.create(body)
        return ControllerHelper.response(responseService)
    }

}