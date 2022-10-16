package com.virgen.peregrina.demo.data.converter

import com.virgen.peregrina.demo.data.entity.Testimony
import com.virgen.peregrina.demo.data.model.TestimonyModel
import com.virgen.peregrina.demo.repository.PilgrimageRepository
import com.virgen.peregrina.demo.repository.ReplicaRepository
import com.virgen.peregrina.demo.repository.UserRepository
import com.virgen.peregrina.demo.util.PILGRIMAGE_REPOSITORY_NAME
import com.virgen.peregrina.demo.util.component.Converter
import com.virgen.peregrina.demo.util.getLog
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Component
import java.text.SimpleDateFormat
import java.util.*

@Component("testimonyConverter")
class TestimonyConverter : Converter<TestimonyModel, Testimony> {

    @Autowired
    @Qualifier("userRepository")
    private lateinit var userRepository: UserRepository

    @Autowired
    @Qualifier(PILGRIMAGE_REPOSITORY_NAME)
    private lateinit var pilgrimageRepository: PilgrimageRepository


    companion object {
        private const val TAG = "[TestimonyConverter] ->"
    }

    override fun toEntity(model: TestimonyModel): Optional<Testimony> = try {
        val sdf = SimpleDateFormat("dd/MM/yyyy")
        val user = userRepository.getReferenceById(model.user_id)
        val pilgrimage = pilgrimageRepository.getReferenceById(model.pilgrimage_id)

        val entity = model.run {
            Testimony(
                id = id,
                user = user,
                pilgrimage = pilgrimage,
                date = sdf.parse(date),
                value = value
            )
        }
        Optional.of(entity)
    } catch (ex: Exception) {
        getLog<TestimonyConverter>().info("$TAG toEntity(): Exception -> $ex")
        Optional.empty<Testimony>()
    }


    override fun toModel(entity: Testimony): Optional<TestimonyModel> = try {
        val sdf = SimpleDateFormat("dd/MM/yyyy")
        val model = entity.run {
            TestimonyModel(
                id = id,
                date = sdf.format(date),
                user_id = user.id!!,
                user_name = user.name,
                pilgrimage_id = pilgrimage.id!!,
                value = value
            )
        }
        Optional.of(model)
    } catch (ex: Exception) {
        getLog<TestimonyConverter>().info("$TAG toEntity(): Exception -> $ex")
        Optional.empty<TestimonyModel>()
    }


}