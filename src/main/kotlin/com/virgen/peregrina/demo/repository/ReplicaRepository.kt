package com.virgen.peregrina.demo.repository

import com.virgen.peregrina.demo.data.entity.Pilgrimage
import com.virgen.peregrina.demo.data.entity.Replica
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.time.LocalDate

@Repository("replicaRepository")
interface ReplicaRepository : JpaRepository<Replica, Long> {

//    fun findAll(pageable: Pageable): Page<Replica>

//    fun findByReplicaIdAndEndDateAfter(replicaId: Long, date: LocalDate = LocalDate.now()): Array<Pilgrimage>?
}