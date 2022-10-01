package com.virgen.peregrina.demo.util.component

import java.util.*

interface Converter<T : Any, V : Any> {

    fun toEntity(model: T): Optional<V>
    fun toModel(entity: V): Optional<T>

}