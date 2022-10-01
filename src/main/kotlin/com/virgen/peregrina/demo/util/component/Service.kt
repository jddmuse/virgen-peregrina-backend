package com.virgen.peregrina.demo.util.component

import com.virgen.peregrina.demo.util.base.BaseResult

interface Service<T : Any> {

    fun create(model: T): BaseResult<T>
    fun delete(model: T): BaseResult<Boolean>
    fun get(id: Long): BaseResult<T>
    fun getAll():BaseResult<List<T>>
//    fun update(model: T): T
//    fun get(model: T)

}