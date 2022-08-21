package com.virgen.peregrina.demo.repository

import com.virgen.peregrina.demo.data.entity.Replica
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository("replicaRepository")
interface ReplicaRepository : JpaRepository<Replica, Long> {


}