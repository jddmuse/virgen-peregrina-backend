package com.virgen.peregrina.demo.service.user

import com.virgen.peregrina.demo.data.converter.UserConverter
import com.virgen.peregrina.demo.data.entity.User
import com.virgen.peregrina.demo.data.model.user.UserModel
import com.virgen.peregrina.demo.data.model.user.entity
import com.virgen.peregrina.demo.data.model.user.model
import com.virgen.peregrina.demo.data.request.CreateUserRequest
import com.virgen.peregrina.demo.repository.UserRepository
import com.virgen.peregrina.demo.service.replica.ReplicaService
import com.virgen.peregrina.demo.util.base.BaseServiceResponse
import com.virgen.peregrina.demo.util.enums.EnumServiceError
import org.apache.commons.logging.LogFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Service

@Service("userService")
class UserServiceImpl : UserService {

    companion object {
        private const val TAG = "UserService ->"
    }

    private val log = LogFactory.getLog(UserServiceImpl::class.java)

    @Autowired
    @Qualifier("userRepository")
    private lateinit var userRepository: UserRepository

    @Autowired
    @Qualifier("replicaService")
    private lateinit var replicaService: ReplicaService

    @Autowired
    @Qualifier("userConverter")
    private lateinit var userConverter: UserConverter

    override fun get(email: String, pass: String): BaseServiceResponse<UserModel> {
        return try {
            val entity = userRepository.findByEmailAndPass(email, pass)?.first()
            if(entity != null) {
                BaseServiceResponse.Success(entity.model())
            } else {
                BaseServiceResponse.NullOrEmptyData(EnumServiceError.USER_LO.info)
            }
        } catch (ex:Exception) {
            log.error("$TAG create(): Exception -> $ex")
            BaseServiceResponse.Error(ex, EnumServiceError.USER_GE.info)
        }
    }

    override fun create(request: CreateUserRequest): BaseServiceResponse<UserModel> {
        return try {
            val userEntity: User = userRepository.save(request.entity())
            BaseServiceResponse.Success(userEntity.model())
        } catch (ex:Exception) {
            log.error("$TAG create(): Exception -> $ex")
            BaseServiceResponse.Error(ex, EnumServiceError.USER_CR.info)
        }
    }

}