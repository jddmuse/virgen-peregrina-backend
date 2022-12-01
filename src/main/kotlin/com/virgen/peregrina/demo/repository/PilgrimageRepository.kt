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
    fun getAllAfterToday(currentDate: Date = Date()): Optional<List<Pilgrimage>?>

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
    fun getInProgressPilgrimageByReplica(replica_id: Long): Optional<Pilgrimage?>

    @Query(
        value = "SELECT * FROM public.pilgrimage p WHERE" +
                " p.user_id = :user_id" +
                " AND (" +
                " p.pilg_date_start = CURRENT_DATE" +
                " OR (" +
                " AND CURRENT_DATE > p.pilg_date_start" +
                " AND CURRENT_DATE < p.pilg_date_end" +
                " )" +
                " ) ORDER BY p.pilg_id DESC LIMIT 1",
        nativeQuery = true
    )
    fun getInProgressPilgrimageByUser(user_id: Long): Optional<Pilgrimage?>

    @Query(
        value = "SELECT * FROM public.pilgrimage p" +
                " JOIN public.replica r ON (p.repl_id = r.repl_id)" +
                " JOIN public.user u ON (r.user_id = u.user_id)" +
                " WHERE u.user_id = :user_id" +
                " AND (" +
                " p.pilg_date_start = CURRENT_DATE" +
                " OR (" +
                " AND CURRENT_DATE > p.pilg_date_start" +
                " AND CURRENT_DATE < p.pilg_date_end" +
                " )" +
                " ) ORDER BY p.pilg_id DESC LIMIT 1",
        nativeQuery = true
    )
    fun getInProgressPilgrimageByReplicaOwnerUser(user_id: Long): Optional<Pilgrimage?>

    @Query(
        value = "SELECT * FROM public.pilgrimage p" +
                " JOIN public.replica r ON (p.repl_id = r.repl_id)" +
                " JOIN public.user u ON (r.user_id = u.user_id)" +
                " WHERE u.user_id = :user_id" +
                " AND (" +
                " p.pilg_date_end = CURRENT_DATE" +
                " OR p.pilg_date_end < CURRENT_DATE" +
                " )" +
                "AND p.pilg_is_returned = false",
        nativeQuery = true
    )
    fun getFinishedPilgrimageAndUnreturnedReplicaByReplicaOwnerUser(user_id: Long): Optional<Pilgrimage?>

    @Query(
        value = " SELECT * FROM public.pilgrimage p" +
                " WHERE p.user_id = :user_id" +
                " AND (" +
                " p.pilg_date_end = CURRENT_DATE" +
                " OR p.pilg_date_end < CURRENT_DATE" +
                " )" +
                "AND p.pilg_is_returned = false",
        nativeQuery = true
    )
    fun getFinishedPilgrimageAndUnreturnedReplicaByUser(user_id: Long): Optional<Pilgrimage?>

    @Query(
        value = "SELECT * FROM public.pilgrimage p WHERE p.user_id = :user_id",
        nativeQuery = true
    )
    fun getAllByUserId(user_id: Long): Optional<List<Pilgrimage>?>

    @Query(
        value = "SELECT * FROM public.pilgrimage p ORDER BY p.pilg_id DESC LIMIT :limit",
        nativeQuery = true
    )
    fun findAllWithLimit(limit: Int = 30): Optional<List<Pilgrimage>?>

}