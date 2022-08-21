package com.virgen.peregrina.demo.service.implement

import com.virgen.peregrina.demo.data.entity.toEntity
import com.virgen.peregrina.demo.data.model.UserModel
import com.virgen.peregrina.demo.data.model.toModel
import com.virgen.peregrina.demo.repository.UserRepository
import com.virgen.peregrina.demo.service.UserService
import com.virgen.peregrina.demo.util.base.BaseResult
import com.virgen.peregrina.demo.util.METHOD_CALLED
import com.virgen.peregrina.demo.util.PARAMS
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

    override fun signIn(firebaseUid: String): BaseResult<UserModel> = try {
        log.info("$TAG $METHOD_CALLED signIn()")
        log.info("$PARAMS $firebaseUid")
        val result = userRepository.getReferenceByFirebaseUid(firebaseUid)
        if (result.isPresent) {
            val userModel = result.get().toModel()
            BaseResult.Success(userModel)
        } else {
            BaseResult.NullOrEmptyData()
        }
    } catch (ex: Exception) {
        log.error(ex)
        BaseResult.Error(ex)
    }

    override fun create(model: UserModel): BaseResult<UserModel> = try {
        log.info("$TAG $METHOD_CALLED create()")
        log.info("$PARAMS $model")
        val result = userRepository.save(model.toEntity())
        BaseResult.Success(result.toModel())
    } catch (ex: Exception) {
        log.error("delete(): Exception -> $ex")
        BaseResult.Error(ex)
    }

    override fun delete(model: UserModel) = try {
        log.info("$METHOD_CALLED delete()")
        log.info("$PARAMS $model")
        userRepository.delete(model.toEntity())
        BaseResult.Success(true)
    } catch (ex: Exception) {
        log.error("delete(): Exception -> $ex")
        BaseResult.Error(ex)
    }

    override fun get(model: UserModel): BaseResult<UserModel> {
        TODO("Not yet implemented")
    }

    override fun getAll(): BaseResult<List<UserModel>> {
        TODO("Not yet implemented")
    }

}