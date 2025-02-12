package com.virgen.peregrina.demo.data.request

import com.fasterxml.jackson.annotation.JsonProperty
import com.virgen.peregrina.demo.data.model.UserModel

data class CreateUserRequest(
    @JsonProperty("name") private val _name: String,
    @JsonProperty("lastName") private val _lastName: String,
    @JsonProperty("email") private val _email: String,
    @JsonProperty("address") private val _address: String,
    @JsonProperty("city") private val _city: String,
    @JsonProperty("country") private val _country: String,
    @JsonProperty("cellphone") private val _cellphone: String,
    @JsonProperty("telephone") private val _telephone: String?,
    @JsonProperty("photoUrl") private val _photoUrl: String?,
    @JsonProperty("pass") private val _pass: String
) {
    val name: String get() = _name.uppercase()
    val lastName: String get() = _lastName.uppercase()
    val email: String get() = _email.uppercase()
    val address: String get() = _address.uppercase()
    val city: String get() = _city.uppercase()
    val country: String get() = _country.uppercase()
    val cellphone: String get() = _cellphone.uppercase()
    val telephone: String? get() = _telephone?.uppercase()
    val photoUrl: String? get() = _photoUrl
    val pass: String get() = _pass

}

fun CreateUserRequest.toModel(): UserModel {
    return UserModel(
        id = -1,
        name = this.name,
        lastName = this.lastName,
        email = this.email,
        address = this.address,
        city = this.city,
        country = this.country,
        cellphone = this.cellphone,
        telephone = this.telephone,
        photoUrl = this.photoUrl,
        pass = pass
    )
}