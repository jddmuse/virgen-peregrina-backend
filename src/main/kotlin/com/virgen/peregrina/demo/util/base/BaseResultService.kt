package com.virgen.peregrina.demo.util.base

sealed class BaseResultService<out T : Any> {
    class Success<out T : Any>(val data: T?) : BaseResultService<T>()
    class Error(val exception: Throwable) : BaseResultService<Nothing>()
    class NullOrEmptyData() : BaseResultService<Nothing>()
}