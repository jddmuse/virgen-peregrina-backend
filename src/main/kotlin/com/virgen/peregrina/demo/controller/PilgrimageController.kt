package com.virgen.peregrina.demo.controller

import com.virgen.peregrina.demo.data.model.PilgrimageModel
import com.virgen.peregrina.demo.service.PilgrimageService
import com.virgen.peregrina.demo.util.METHOD_CALLED
import com.virgen.peregrina.demo.util.PARAMS
import com.virgen.peregrina.demo.util.PILGRIMAGE_SERVICE_NAME
import com.virgen.peregrina.demo.util.base.BaseResponse
import com.virgen.peregrina.demo.util.base.BaseResult
import org.apache.commons.logging.LogFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.http.HttpStatus
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

    @PostMapping("/create")
    fun create(@RequestBody pilgrimageModel: PilgrimageModel): ResponseEntity<BaseResponse<PilgrimageModel>> = try {
        log.info("$TAG $METHOD_CALLED create()")
        log.info("$PARAMS $pilgrimageModel")
        when (val result = pilgrimageService.create(pilgrimageModel)) {
            is BaseResult.Success -> {
                val successMessage = "Peregrinación creada exitosamente"
                ResponseEntity(BaseResponse(result.data, successMessage), HttpStatus.OK)
            }

            is BaseResult.Error -> {
                val errorMessage = "Error interno al crear la peregrinación: ${result.exception.message}"
                ResponseEntity(BaseResponse(error = errorMessage), HttpStatus.INTERNAL_SERVER_ERROR)
            }

            is BaseResult.NullOrEmptyData -> {
                val badRequestMessage = "Datos inválidos proporcionados para la creación de la peregrinación"
                ResponseEntity(BaseResponse(message = badRequestMessage), HttpStatus.BAD_REQUEST)
            }
        }
    } catch (ex: Exception) {
        log.error("$TAG create(): Excepción -> $ex")
        val errorMessage = "Error interno del servidor: ${ex.message}"
        ResponseEntity(BaseResponse(error = errorMessage), HttpStatus.BAD_REQUEST)
    }


    @GetMapping("/get-all")
    fun getAll(): ResponseEntity<BaseResponse<List<PilgrimageModel>>> = try {
        log.info("$TAG $METHOD_CALLED getAll()")
        when (val result = pilgrimageService.getAll()) {
            is BaseResult.Success -> {
                val successMessage = "Todas las peregrinaciones fueron obtenidas exitosamente"
                ResponseEntity(BaseResponse(result.data, successMessage), HttpStatus.OK)
            }

            is BaseResult.Error -> {
                val errorMessage = "Error al obtener todas las peregrinaciones: ${result.exception.message}"
                ResponseEntity(BaseResponse(error = errorMessage), HttpStatus.BAD_REQUEST)
            }

            is BaseResult.NullOrEmptyData -> {
                val badRequestMessage = "No se encontraron peregrinaciones"
                ResponseEntity(BaseResponse(message = badRequestMessage), HttpStatus.BAD_REQUEST)
            }
        }
    } catch (ex: Exception) {
        log.error("$TAG getAll(): Excepción -> $ex")
        val errorMessage = "Error interno del servidor al obtener todas las peregrinaciones: ${ex.message}"
        ResponseEntity(BaseResponse(error = errorMessage), HttpStatus.BAD_REQUEST)
    }


    @GetMapping("/get-all-limit/{limit}")
    fun getAllWithLimit(
            @PathVariable limit: Int = 30
    ): ResponseEntity<BaseResponse<List<PilgrimageModel>>> = try {
        log.info("$TAG $METHOD_CALLED getAllWithLimit()")
        when (val result = pilgrimageService.getAllWithLimit(limit)) {
            is BaseResult.Success -> {
                val successMessage = "Todas las peregrinaciones obtenidas exitosamente con límite: $limit"
                ResponseEntity(BaseResponse(result.data, successMessage), HttpStatus.OK)
            }

            is BaseResult.Error -> {
                val errorMessage = "Error al obtener todas las peregrinaciones con límite $limit: ${result.exception.message}"
                ResponseEntity(BaseResponse(error = errorMessage), HttpStatus.BAD_REQUEST)
            }

            is BaseResult.NullOrEmptyData -> {
                val badRequestMessage = "No se encontraron peregrinaciones con el límite $limit"
                ResponseEntity(BaseResponse(message = badRequestMessage), HttpStatus.BAD_REQUEST)
            }
        }
    } catch (ex: Exception) {
        log.error("$TAG getAllWithLimit(): Excepción -> $ex")
        val errorMessage = "Error interno del servidor al obtener todas las peregrinaciones con límite $limit: ${ex.message}"
        ResponseEntity(BaseResponse(error = errorMessage), HttpStatus.BAD_REQUEST)
    }


}