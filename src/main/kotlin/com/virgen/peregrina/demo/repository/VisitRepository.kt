package com.virgen.peregrina.demo.repository

import com.virgen.peregrina.demo.data.entity.Visit
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import java.util.*

@Repository("visitRepository")
interface VisitRepository : JpaRepository<Visit, Long> {

    @Query(
            value = "SELECT * FROM public.visit WHERE visi_date_end >= :currentDate OR visi_date_start >= :currentDate",
            nativeQuery = true
    )
    fun getAllAfterToday(currentDate: Date = Date()): Optional<List<Visit>>

}