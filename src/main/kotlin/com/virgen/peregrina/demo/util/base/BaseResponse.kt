package com.virgen.peregrina.demo.util.base

data class BaseResponse<T>(
        val data: T? = null,
        val message: String? = null,
        val error: String? = null,
)