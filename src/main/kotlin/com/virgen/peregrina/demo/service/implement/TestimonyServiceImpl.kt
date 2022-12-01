package com.virgen.peregrina.demo.service.implement

import com.virgen.peregrina.demo.data.converter.TestimonyConverter
import com.virgen.peregrina.demo.data.model.TestimonyModel
import com.virgen.peregrina.demo.repository.TestimonyRepository
import com.virgen.peregrina.demo.service.TestimonyService
import com.virgen.peregrina.demo.util.METHOD_CALLED
import com.virgen.peregrina.demo.util.PARAMS
import com.virgen.peregrina.demo.util.base.BaseResult
import com.virgen.peregrina.demo.util.getCurrentDate
import com.virgen.peregrina.demo.util.getLog
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Service
import java.util.Calendar

@Service("testimonyService")
class TestimonyServiceImpl : TestimonyService {

    @Autowired
    @Qualifier("testimonyRepository")
    private lateinit var testimonyRepository: TestimonyRepository

    @Autowired
    @Qualifier("testimonyConverter")
    private lateinit var testimonyConverter: TestimonyConverter

    companion object {
        private const val TAG = "TestimonyServiceImpl ->"
    }

    private val log = getLog<TestimonyServiceImpl>()

    override fun create(model: TestimonyModel): BaseResult<TestimonyModel> = try {
        log.info("$TAG $METHOD_CALLED create() $PARAMS $model")
//        model.date = getCurrentDate()
        val newEntity = testimonyRepository.save(testimonyConverter.toEntity(model).get())
        BaseResult.Success(testimonyConverter.toModel(newEntity).get())
    } catch (ex: Exception) {
        log.error("$TAG create(): Exception -> $ex")
        BaseResult.Error(ex)
    }


    override fun delete(model: TestimonyModel): BaseResult<Boolean> {
        TODO("Not yet implemented")
    }

    override fun get(id: Long): BaseResult<TestimonyModel> {
        TODO("Not yet implemented")
    }

    override fun getAll(): BaseResult<List<TestimonyModel>> = try {
        val result = testimonyRepository.findAll()
        val data = result.map { testimonyConverter.toModel(it).get() }
        BaseResult.Success(data)
    } catch (ex: Exception) {
        log.error("$TAG getAll(): Exception -> $ex")
        BaseResult.Error(ex)
    }

    override fun getAll(replica_id: Long): BaseResult<List<TestimonyModel>> = try {
        val result = testimonyRepository.getAllByReplica(replica_id).get()
        val data = result.map { testimonyConverter.toModel(it).get() }
        BaseResult.Success(data)
    } catch (ex: Exception) {
        log.error("$TAG getAll(): Exception -> $ex")
        BaseResult.Error(ex)
    }

}