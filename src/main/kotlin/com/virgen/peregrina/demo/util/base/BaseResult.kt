package com.virgen.peregrina.demo.util.base

sealed class BaseResult<out T : Any> {
    data class Success<out T : Any>(val data: T?, val message: String? = null) : BaseResult<T>()
    data class Error(val exception: Throwable, val message: String? = null) : BaseResult<Nothing>()
    class NullOrEmptyData(val message: String? = null) : BaseResult<Nothing>()
}
