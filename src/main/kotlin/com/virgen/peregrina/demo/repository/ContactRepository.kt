package com.virgen.peregrina.demo.repository

import com.virgen.peregrina.demo.data.entity.Contact
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ContactRepository : JpaRepository<Contact, Long>