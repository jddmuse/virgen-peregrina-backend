package com.virgen.peregrina.demo.util.base

sealed class BaseResult<out T : Any> {
    class Success<out T : Any>(val data: T?) : BaseResult<T>()
    class Error(val exception: Throwable, val message: String? = null) : BaseResult<Nothing>()
    class NullOrEmptyData(val message: String? = null) : BaseResult<Nothing>()
}