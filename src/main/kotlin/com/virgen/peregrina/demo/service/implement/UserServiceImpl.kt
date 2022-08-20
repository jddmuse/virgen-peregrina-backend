package com.virgen.peregrina.demo.service.implement

import com.virgen.peregrina.demo.data.entity.toEntity
import com.virgen.peregrina.demo.data.model.UserModel
import com.virgen.peregrina.demo.data.model.toModel
import com.virgen.peregrina.demo.repository.UserRepository
import com.virgen.peregrina.demo.service.UserService
import com.virgen.peregrina.demo.util.base.BaseResultService
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

    override fun signIn(firebaseUid: String): BaseResultService<UserModel> = try {
        log.debug("$TAG $METHOD_CALLED signIn()")
        log.debug("$PARAMS $firebaseUid")
        val result = userRepository.getReferenceByFirebaseUid(firebaseUid)
        if (result.isPresent) {
            val userModel = result.get().toModel()
            BaseResultService.Success(userModel)
        } else {
            BaseResultService.NullOrEmptyData()
        }
    } catch (ex: Exception) {
        log.error(ex)
        BaseResultService.Error(ex)
    }

    override fun create(model: UserModel): BaseResultService<UserModel> = try {
        log.debug("$TAG $METHOD_CALLED create()")
        log.debug("$PARAMS $model")
        val result = userRepository.save(model.toEntity())
        BaseResultService.Success(result.toModel())
    } catch (ex: Exception) {
        log.error("delete(): Exception -> $ex")
        BaseResultService.Error(ex)
    }

    override fun delete(model: UserModel) = try {
        log.debug("$METHOD_CALLED delete()")
        log.debug("$PARAMS $model")
        userRepository.delete(model.toEntity())
        BaseResultService.Success(true)
    } catch (ex: Exception) {
        log.error("delete(): Exception -> $ex")
        BaseResultService.Error(ex)
    }

    override fun get(model: UserModel): BaseResultService<UserModel> {
        TODO("Not yet implemented")
    }

    override fun getAll(): BaseResultService<List<UserModel>> {
        TODO("Not yet implemented")
    }

}