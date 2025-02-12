package com.virgen.peregrina.demo.controller

import com.virgen.peregrina.demo.controller.util.ControllerHelper
import com.virgen.peregrina.demo.data.model.TestimonyModel
import com.virgen.peregrina.demo.data.request.CreateTestimonyRequest
import com.virgen.peregrina.demo.data.request.GetTestimonyRequest
import com.virgen.peregrina.demo.data.request.toModel
import com.virgen.peregrina.demo.data.response.CreateTestimonyResponse
import com.virgen.peregrina.demo.data.response.GetTestimonyResponse
import com.virgen.peregrina.demo.service.testimony.TestimonyService
import com.virgen.peregrina.demo.util.base.BaseApiResponse
import com.virgen.peregrina.demo.util.getLog
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/testimony")
class TestimonyController {

    @Autowired
    @Qualifier("testimonyService")
    private lateinit var testimonyService: TestimonyService

    companion object {
        private const val TAG = "TestimonyController ->"
    }

    private val log = getLog<TestimonyController>()

    @GetMapping("/get")
    fun get(body: GetTestimonyRequest): BaseApiResponse<GetTestimonyResponse> {
        return BaseApiResponse()
    }

    @GetMapping("/getAll")
    fun getAll(): BaseApiResponse<List<GetTestimonyResponse>> {
        return BaseApiResponse()
    }

    @PostMapping("/create")
    fun create(body: CreateTestimonyRequest): ResponseEntity<BaseApiResponse<TestimonyModel>> {
        val serviceResponse = testimonyService.create(body.toModel())
        return ControllerHelper.response(serviceResponse)
    }

    @GetMapping("/update")
    fun update(body: CreateTestimonyRequest): BaseApiResponse<CreateTestimonyResponse> {
        return BaseApiResponse()
    }



}