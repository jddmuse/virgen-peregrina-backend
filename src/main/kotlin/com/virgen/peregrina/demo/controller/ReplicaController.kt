package com.virgen.peregrina.demo.controller

import com.virgen.peregrina.demo.data.entity.Replica
import com.virgen.peregrina.demo.data.model.ReplicaModel
import com.virgen.peregrina.demo.service.ReplicaService
import com.virgen.peregrina.demo.util.METHOD_CALLED
import com.virgen.peregrina.demo.util.PARAMS
import com.virgen.peregrina.demo.util.base.BaseResponse
import com.virgen.peregrina.demo.util.base.BaseResult
import org.apache.commons.logging.LogFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.http.HttpStatus
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

    @PostMapping("/create")
    fun createReplica(@RequestBody model: ReplicaModel): ResponseEntity<BaseResponse<ReplicaModel>> = try {
        log.info("$TAG $METHOD_CALLED createReplica()")
        log.info("$PARAMS $model")
        when (val result = replicaService.create(model)) {
            is BaseResult.Success -> {
                val successMessage = "Réplica creada exitosamente"
                ResponseEntity(BaseResponse(result.data, successMessage), HttpStatus.OK)
            }

            is BaseResult.Error -> {
                val errorMessage = "Error al crear la réplica: ${result.exception.message}"
                ResponseEntity(BaseResponse(error = errorMessage), HttpStatus.INTERNAL_SERVER_ERROR)
            }

            is BaseResult.NullOrEmptyData -> {
                val badRequestMessage = "Datos inválidos proporcionados para la creación de la réplica"
                ResponseEntity(BaseResponse(message = badRequestMessage), HttpStatus.BAD_REQUEST)
            }
        }
    } catch (ex: Exception) {
        log.error("$TAG createReplica(): Excepción -> $ex")
        val errorMessage = "Error interno del servidor: ${ex.message}"
        ResponseEntity(BaseResponse(error = errorMessage), HttpStatus.INTERNAL_SERVER_ERROR)
    }


    @GetMapping("/get-all")
    fun getAll(): ResponseEntity<BaseResponse<List<ReplicaModel>>> = try {
        log.debug("$TAG, $METHOD_CALLED getAll()")
        when (val result = replicaService.getAll()) {
            is BaseResult.Success -> {
                val successMessage = "Réplicas obtenidas exitosamente"
                ResponseEntity(BaseResponse(result.data, successMessage), HttpStatus.OK)
            }

            is BaseResult.Error -> {
                val errorMessage = "Error interno al obtener réplicas: ${result.exception.message}"
                ResponseEntity(BaseResponse(error = errorMessage), HttpStatus.INTERNAL_SERVER_ERROR)
            }

            is BaseResult.NullOrEmptyData -> {
                val badRequestMessage = "No se encontraron réplicas"
                ResponseEntity(BaseResponse(message = badRequestMessage), HttpStatus.BAD_REQUEST)
            }
        }
    } catch (ex: Exception) {
        log.error("$TAG getAll(): Excepción -> $ex")
        val errorMessage = "Error interno del servidor al obtener réplicas: ${ex.message}"
        ResponseEntity(BaseResponse(error = errorMessage), HttpStatus.INTERNAL_SERVER_ERROR)
    }


}