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

    @Query(
        value = "SELECT * FROM public.pilgrimage p WHERE " +
                " (" +
                " p.repl_id = :replica_id " +
                " AND p.pilg_date_start = CURRENT_DATE" +
                " )" +
                " OR (" +
                " p.repl_id = :replica_id " +
                " AND CURRENT_DATE > p.pilg_date_start" +
                " AND CURRENT_DATE < p.pilg_date_end" +
                " ) ORDER BY p.pilg_id DESC LIMIT 1",
        nativeQuery = true
    )
    fun getLastByReplicaId(replica_id: Long): Optional<Pilgrimage?>

}