package com.virgen.peregrina.demo.repository

import com.virgen.peregrina.demo.data.entity.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import java.util.*

@Repository("userRepository")
interface UserRepository : JpaRepository<User, Long> {

    @Query(
            value = "SELECT * FROM public.user WHERE user_firebase_uid = :uid",
            nativeQuery = true
    )
    fun getReferenceByFirebaseUid(uid: String): Optional<User>


}