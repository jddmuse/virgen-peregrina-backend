package com.virgen.peregrina.demo.service.user

import com.virgen.peregrina.demo.data.converter.UserConverter
import com.virgen.peregrina.demo.data.entity.User
import com.virgen.peregrina.demo.data.entity.toModel
import com.virgen.peregrina.demo.data.model.user.UserModel
import com.virgen.peregrina.demo.data.model.user.entity
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
                BaseServiceResponse.Success(entity.toModel())
            } else {
                BaseServiceResponse.NullOrEmptyData(EnumServiceError.USER_LO.info)
            }
        } catch (ex:Exception) {
            log.error("$TAG create(): Exception -> $ex")
            BaseServiceResponse.Error(ex, EnumServiceError.USER_GE.info)
        }
    }

    override fun create(model: UserModel): BaseServiceResponse<UserModel> {
        return try {
            val userEntity: User = userRepository.save(model.entity())
            BaseServiceResponse.Success(userEntity.toModel())
        } catch (ex:Exception) {
            log.error("$TAG create(): Exception -> $ex")
            BaseServiceResponse.Error(ex, EnumServiceError.USER_CR.info)
        }
    }

//    override fun login(uuid: String): BaseResult<UserModel> = try {
//        log.info("$TAG $METHOD_CALLED login()")
//        log.info("$PARAMS $uuid")
//        val result = userRepository.getReferenceByUUID(uuid)
//        if (result.isPresent) {
//            val userModel = userConverter.toModel(result.get()).get()
//            BaseResult.Success(userModel)
//        } else {
//            BaseResult.NullOrEmptyData()
//        }
//    } catch (ex: Exception) {
//        log.error("$TAG login(): Exception -> $ex")
//        BaseResult.Error(ex)
//    }
//
//    override fun getAllPilgrims(): BaseResult<List<UserModel>> = try {
//        log.info("$TAG $METHOD_CALLED getAllPilgrims()")
//        val result = userRepository.getAllPilgrims().get()
//        val list = result.map { userConverter.toModel(it).get() }
//        BaseResult.Success(list)
//    } catch (ex: Exception) {
//        log.error("$TAG getAllPilgrims(): Exception -> $ex")
//        BaseResult.Error(ex)
//    }
//
//    override fun update(model: UserModel): BaseResult<UserModel> = try {
//        log.info("$TAG $METHOD_CALLED update() $PARAMS $model")
//        val entity = userConverter.toEntity(model).get()
//        userRepository.save(entity)
//        BaseResult.Success(userConverter.toModel(entity).get())
//    } catch (ex: Exception) {
//        log.error("$TAG update(): Exception -> $ex")
//        BaseResult.Error(ex)
//    }
//
//    override fun create(userModel: UserModel): BaseResult<UserModel> = try {
//        log.info("$TAG $METHOD_CALLED create() $PARAMS $userModel")
//
//        val replicasModelList = userModel.replicas
//        val replicasModelListResult = mutableListOf<ReplicaModel>()
//
//        userModel.replicas = null
//
//        val userEntity = userRepository.save(userConverter.toEntity(userModel).get())
//        val userModelResult = userConverter.toModel(userEntity).get()
//
//        replicasModelList?.forEach { replicaModel ->
//            replicaModel.user_id = userEntity.id!!
//            replicaModel.user_name = userEntity.name
//
//            replicaService.create(replicaModel)
//            replicasModelListResult.add(replicaModel)
//        }
//
//        userModelResult.replicas = replicasModelListResult
//        BaseResult.Success(userModelResult)
//    } catch (ex: Exception) {
//        log.error("$TAG create(): Exception -> $ex")
//        BaseResult.Error(ex)
//    }
//
//    override fun delete(model: UserModel) = try {
//        log.info("$METHOD_CALLED delete() $PARAMS $model")
//        userRepository.delete(userConverter.toEntity(model).get())
//        BaseResult.Success(true)
//    } catch (ex: Exception) {
//        log.error("delete(): Exception -> $ex")
//        BaseResult.Error(ex)
//    }
//
//    override fun get(id: Long): BaseResult<UserModel> = try {
//        log.info("$METHOD_CALLED get() $PARAMS id=$id")
//        val result = userRepository.getReferenceById(id)
//        BaseResult.Success(userConverter.toModel(result).get())
//    } catch (ex: Exception) {
//        log.error("$TAG get(): Exception -> $ex")
//        BaseResult.Error(ex)
//    }
//
//    override fun getAll(): BaseResult<List<UserModel>> = try {
//        val result = userRepository.findAll()
//        val data = result.map {
//            userConverter.toModel(it).get()
//        }
//        BaseResult.Success(data)
//    } catch (ex: Exception) {
//        log.error("$TAG getAll(): Exception -> $ex")
//        BaseResult.Error(ex) // return
//    }


}