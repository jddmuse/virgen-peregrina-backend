package com.virgen.peregrina.demo.util.component

import com.virgen.peregrina.demo.util.base.BaseServiceResponse

interface Service<T : Any> {

    fun create(model: T): BaseServiceResponse<T> {
        return BaseServiceResponse.NullOrEmptyData()
    }

    fun delete(model: T): BaseServiceResponse<Boolean> {
        return BaseServiceResponse.NullOrEmptyData()
    }

    fun get(id: Long): BaseServiceResponse<T> {
        return BaseServiceResponse.NullOrEmptyData()
    }

    fun getAll(): BaseServiceResponse<List<T>> {
        return BaseServiceResponse.NullOrEmptyData()
    }

}