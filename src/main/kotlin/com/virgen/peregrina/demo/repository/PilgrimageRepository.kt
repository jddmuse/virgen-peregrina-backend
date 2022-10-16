package com.virgen.peregrina.demo.repository

import com.virgen.peregrina.demo.data.entity.Pilgrimage
import com.virgen.peregrina.demo.util.PILGRIMAGE_REPOSITORY_NAME
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import java.util.*

@Repository(PILGRIMAGE_REPOSITORY_NAME)
interface PilgrimageRepository : JpaRepository<Pilgrimage, Long> {

    @Query(
            value = "SELECT * FROM public.pilgrimage WHERE pilg_date_end >= :currentDate OR pilg_date_start >= :currentDate",
            nativeQuery = true
    )
    fun getAllAfterToday(currentDate: Date = Date()): Optional<List<Pilgrimage>>

}