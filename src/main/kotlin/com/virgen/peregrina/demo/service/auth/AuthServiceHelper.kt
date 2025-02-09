package com.virgen.peregrina.demo.service.auth

import com.virgen.peregrina.demo.data.model.LoginModel
import com.virgen.peregrina.demo.data.model.UserModel

internal class AuthServiceHelper {

    companion object {
        fun loginModel(userModel: UserModel): LoginModel {
            return LoginModel(
                userName = userModel.name,
                userEmail = userModel.email,
                userAddress = userModel.address,
                userCity = userModel.city,
                userCountry = userModel.country,
                userCellphone = userModel.cellphone
            )
        }
    }


}