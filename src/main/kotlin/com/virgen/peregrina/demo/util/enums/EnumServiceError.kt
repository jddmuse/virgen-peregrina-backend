package com.virgen.peregrina.demo.util.enums

enum class EnumServiceError(val info: String) {
    USER_CR("No se pudo crear el usuario"),
    USER_GE("No se encontró un usuario registrado con el email enviado"),
    USER_LO("Email o contraseña invalidos")
}