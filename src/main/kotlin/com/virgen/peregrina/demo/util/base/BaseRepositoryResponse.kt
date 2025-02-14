package com.virgen.peregrina.demo.util.base

import com.fasterxml.jackson.annotation.JsonProperty

class BaseRepositoryResponse<T: Any?> {

    constructor()

    constructor(data: T?, message: String, success: Boolean) {
        this.data = data
        this.message = message
        this.success = success
    }

    @JsonProperty("data") private var _data: T? = null
    @JsonProperty("message") private var _message: String = ""
    @JsonProperty("success") private var _success: Boolean = false

    var data
        get() = _data
        set(value) { _data = value }

    var message
        get() = _message
        set(value) { _message = value }

    var success
        get() = _success
        set(value) { _success = value }
}