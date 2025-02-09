package com.virgen.peregrina.demo.controller

import com.virgen.peregrina.demo.util.base.BaseApiResponse
import com.virgen.peregrina.demo.util.base.BaseServiceResponse
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity

class ControllerHelper {
    companion object {
        fun <T : Any> response(serviceResponse: BaseServiceResponse<T>): ResponseEntity<BaseApiResponse<T>> {
            return when (serviceResponse) {
                is BaseServiceResponse.Success ->
                    ResponseEntity(
                        BaseApiResponse(
                            data = serviceResponse.data,
                            message = "Exito"
                        ), HttpStatus.OK
                    )

                is BaseServiceResponse.Error ->
                    ResponseEntity(
                        BaseApiResponse(
                            message = serviceResponse.message,
                            error = serviceResponse.exception.message
                        ), HttpStatus.BAD_REQUEST
                    )

                is BaseServiceResponse.NullOrEmptyData -> {
                    ResponseEntity(
                        BaseApiResponse(
                            message = serviceResponse.message
                        ), HttpStatus.NOT_FOUND
                    )
                }
            }
        }
    }
}