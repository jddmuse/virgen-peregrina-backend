package com.virgen.peregrina.demo.util.base

sealed class BaseServiceResponse<out T : Any> {
    data class Success<out T : Any>(
        val data: T,
        val message: String? = null
    ) : BaseServiceResponse<T>()

    data class Error(
        val exception: Throwable,
        val message: String? = null
    ) : BaseServiceResponse<Nothing>()

    class NullOrEmptyData(
        val message: String? = null
    ) : BaseServiceResponse<Nothing>()
}
