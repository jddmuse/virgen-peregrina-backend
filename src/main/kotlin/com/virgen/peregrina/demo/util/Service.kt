package com.virgen.peregrina.demo.util

import com.virgen.peregrina.demo.util.base.BaseResultService

interface Service<T : Any> {

    fun create(model: T): BaseResultService<T>
    fun delete(model: T): BaseResultService<Boolean>
    fun get(model: T): BaseResultService<T>
    fun getAll():BaseResultService<List<T>>
//    fun update(model: T): T
//    fun get(model: T)

}