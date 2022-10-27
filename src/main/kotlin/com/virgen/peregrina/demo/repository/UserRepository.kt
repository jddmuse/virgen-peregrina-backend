package com.virgen.peregrina.demo.repository

import com.virgen.peregrina.demo.data.entity.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import java.util.*

@Repository("userRepository")
interface UserRepository : JpaRepository<User, Long> {

    @Query(
        value = "SELECT * FROM public.user WHERE user_uuid = :uid",
        nativeQuery = true
    )
    fun getReferenceByUUID(uid: String): Optional<User?>

    @Query(
        value = "SELECT * FROM public.user WHERE user_is_pilgrim = true",
        nativeQuery = true
    )
    fun getAllPilgrims(): Optional<List<User>?>
}