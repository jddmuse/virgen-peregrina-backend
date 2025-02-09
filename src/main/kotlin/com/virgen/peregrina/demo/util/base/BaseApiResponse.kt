package com.virgen.peregrina.demo.util.base

data class BaseApiResponse<T>(
    val data: T? = null,
    val message: String? = null,
    val error: String? = null,
)