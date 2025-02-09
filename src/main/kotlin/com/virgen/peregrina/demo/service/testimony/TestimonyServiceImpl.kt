package com.virgen.peregrina.demo.service.testimony

import com.virgen.peregrina.demo.data.converter.TestimonyConverter
import com.virgen.peregrina.demo.data.entity.Testimony
import com.virgen.peregrina.demo.data.entity.toModel
import com.virgen.peregrina.demo.data.model.TestimonyModel
import com.virgen.peregrina.demo.repository.PilgrimageRepository
import com.virgen.peregrina.demo.repository.TestimonyRepository
import com.virgen.peregrina.demo.repository.UserRepository
import com.virgen.peregrina.demo.util.base.BaseServiceResponse
import com.virgen.peregrina.demo.util.getLog
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Service

@Service("testimonyService")
class TestimonyServiceImpl : TestimonyService {

    @Autowired
    @Qualifier("testimonyRepository")
    private lateinit var testimonyRepository: TestimonyRepository

    @Autowired
    @Qualifier("pilgrimageRepository")
    private lateinit var pilgrimageRepository: PilgrimageRepository

//    @Autowired
//    @Qualifier("testimonyConverter")
//    private lateinit var testimonyConverter: TestimonyConverter

    companion object {
        private const val TAG = "TestimonyServiceImpl ->"
    }

    private val log = getLog<TestimonyServiceImpl>()

    override fun create(model: TestimonyModel): BaseServiceResponse<TestimonyModel> {
        try {
            val pilgrimageEntity = pilgrimageRepository.findById(model.pilgrimageId)
            if(!pilgrimageEntity.isPresent)
                return BaseServiceResponse.NullOrEmptyData()
            val data2Save = Testimony(
                date = model.date,
                value = model.value,
                pilgrimage = pilgrimageEntity.get()
            )
            val testimonyEntity = testimonyRepository.save(data2Save)
            return BaseServiceResponse.Success(testimonyEntity.toModel())
        } catch (ex:Exception) {
            log.error("$TAG create(): Exception -> $ex")
            return BaseServiceResponse.Error(ex)
        }
    }

//    override fun create(model: TestimonyModel): BaseServiceResponse<TestimonyModel> = try {
//        log.info("$TAG $METHOD_CALLED create() $PARAMS $model")
////        model.date = getCurrentDate()
//        val newEntity = testimonyRepository.save(testimonyConverter.toEntity(model).get())
//        BaseServiceResponse.Success(testimonyConverter.toModel(newEntity).get())
//    } catch (ex: Exception) {
//        log.error("$TAG create(): Exception -> $ex")
//        BaseServiceResponse.Error(ex)
//    }
//
//
//    override fun delete(model: TestimonyModel): BaseServiceResponse<Boolean> {
//        TODO("Not yet implemented")
//    }
//
//    override fun get(id: Long): BaseServiceResponse<TestimonyModel> {
//        TODO("Not yet implemented")
//    }
//
//    override fun getAll(): BaseServiceResponse<List<TestimonyModel>> = try {
//        val result = testimonyRepository.findAll()
//        val data = result.map { testimonyConverter.toModel(it).get() }
//        BaseServiceResponse.Success(data)
//    } catch (ex: Exception) {
//        log.error("$TAG getAll(): Exception -> $ex")
//        BaseServiceResponse.Error(ex)
//    }
//
//    override fun getAll(replica_id: Long): BaseServiceResponse<List<TestimonyModel>> = try {
//        val result = testimonyRepository.getAllByReplica(replica_id).get()
//        val data = result.map { testimonyConverter.toModel(it).get() }
//        BaseServiceResponse.Success(data)
//    } catch (ex: Exception) {
//        log.error("$TAG getAll(): Exception -> $ex")
//        BaseServiceResponse.Error(ex)
//    }

}