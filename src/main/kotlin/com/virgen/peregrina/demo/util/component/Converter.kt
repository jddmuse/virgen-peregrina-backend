package com.virgen.peregrina.demo.util.component

interface Converter<T : Any, V : Any> {

    fun toEntity(model: T): V?
    fun toModel(entity: V): T?

}