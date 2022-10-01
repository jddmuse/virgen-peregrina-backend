package com.virgen.peregrina.demo.service.implement

import com.virgen.peregrina.demo.data.entity.toEntity
import com.virgen.peregrina.demo.data.model.ReplicaModel
import com.virgen.peregrina.demo.data.model.UserModel
import com.virgen.peregrina.demo.data.model.toModel
import com.virgen.peregrina.demo.repository.UserRepository
import com.virgen.peregrina.demo.service.ReplicaService
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

    @Autowired
    @Qualifier("replicaService")
    private lateinit var replicaService: ReplicaService

    override fun login(uuid: String): BaseResult<UserModel> = try {
        log.info("$TAG $METHOD_CALLED login()")
        log.info("$PARAMS $uuid")
        val result = userRepository.getReferenceByUUID(uuid)
        if (result.isPresent) {
            val userModel = result.get().toModel().get()
            BaseResult.Success(userModel)
        } else {
            BaseResult.NullOrEmptyData()
        }
    } catch (ex: Exception) {
        log.error("signIn(): Exception -> $ex")
        BaseResult.Error(ex)
    }

    override fun create(userModel: UserModel): BaseResult<UserModel> = try {
        log.info("$TAG $METHOD_CALLED create() $PARAMS $userModel")

        val replicasModelList = userModel.replicas
        val replicasModelListResult = mutableListOf<ReplicaModel>()

        userModel.replicas = null

        val userEntity = userRepository.save(userModel.toEntity().get())
        val userModelResult = userEntity.toModel().get()

        replicasModelList?.forEach { replicaModel ->
            replicaModel.user = userModelResult
            replicaService.create(replicaModel)
            replicasModelListResult.add(replicaModel)
        }

        userModelResult.replicas = replicasModelListResult
        BaseResult.Success(userModelResult)
    } catch (ex: Exception) {
        log.error("create(): Exception -> $ex")
        BaseResult.Error(ex)
    }

    override fun delete(model: UserModel) = try {
        log.info("$METHOD_CALLED delete() $PARAMS $model")
        userRepository.delete(model.toEntity().get())
        BaseResult.Success(true)
    } catch (ex: Exception) {
        log.error("delete(): Exception -> $ex")
        BaseResult.Error(ex)
    }

    override fun get(id: Long): BaseResult<UserModel> = try {
        log.info("$METHOD_CALLED get() $PARAMS id=$id")
        val result = userRepository.getReferenceById(id)
        BaseResult.Success(result.toModel().get())
    } catch (ex: Exception) {
        log.error("get(): Exception -> $ex")
        BaseResult.Error(ex)
    }


    override fun getAll(): BaseResult<List<UserModel>> = try {
        val result = userRepository.findAll()
        val data = result.map { it.toModel().get() }
        BaseResult.Success(data)
    } catch (ex: Exception) {
        log.error("$TAG getAll(): Exception -> $ex")
        BaseResult.Error(ex) // return
    }

}