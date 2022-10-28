package com.virgen.peregrina.demo.repository

import com.virgen.peregrina.demo.data.entity.Testimony
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import java.util.Optional

@Repository("testimonyRepository")
interface TestimonyRepository : JpaRepository<Testimony, Long> {

    @Query(
        value = "SELECT * FROM public.testimony t " +
                " JOIN public.pilgrimage p ON p.pilg_id = t.pilg_id" +
                " WHERE p.repl_id = :replica_id",
        nativeQuery = true
    )
    fun getAllByReplica(replica_id: Long): Optional<List<Testimony>?>

    @Query(
        value = "SELECT * FROM public.testimony t" +
                " JOIN public.pilgrimage p ON p.pilg_id = t.pilg_id" +
                " WHERE p.pilg_id = :pilgrimage_id",
        nativeQuery = true
    )
    fun getTestimonyByPilgrimage(pilgrimage_id: Long): Optional<Testimony?>


}