package com.virgen.peregrina.demo.service.auth

import com.virgen.peregrina.demo.data.model.LoginModel
import com.virgen.peregrina.demo.data.model.user.model
import com.virgen.peregrina.demo.data.request.LoginRequest
import com.virgen.peregrina.demo.repository.UserRepository
import com.virgen.peregrina.demo.util.base.BaseServiceResponse
import com.virgen.peregrina.demo.util.enums.EnumServiceError
import org.apache.commons.logging.LogFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Service

@Service("authService")
class AuthServiceImpl: AuthService {

    companion object {
        private const val TAG = "AuthService ->"
    }

    private val log = LogFactory.getLog(AuthServiceImpl::class.java)

    @Autowired
    @Qualifier("userRepository")
    private lateinit var userRepository: UserRepository

    override fun login(request: LoginRequest): BaseServiceResponse<LoginModel> {
        return try {
            val userEntity = userRepository.findByEmailAndPass(request.email.uppercase(), request.pass.uppercase())?.first()
            if(userEntity != null) {
                val response = AuthServiceHelper.loginModel(userEntity.model())
                BaseServiceResponse.Success(response)
            } else {
                BaseServiceResponse.NullOrEmptyData(EnumServiceError.USER_LO.info)
            }
        } catch (ex:Exception) {
            log.error("$TAG create(): Exception -> $ex")
            BaseServiceResponse.Error(ex, EnumServiceError.USER_LO.info)
        }
    }
}