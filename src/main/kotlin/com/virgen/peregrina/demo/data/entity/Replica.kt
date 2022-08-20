package com.virgen.peregrina.demo.data.entity

import com.virgen.peregrina.demo.data.model.ReplicaModel
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "replica")
data class Replica(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(unique = true, nullable = false, name = "repl_id")
        val id: Long,

        @Column(name = "repl_state")
        val state:String,

        @Column(name = "repl_required_restore")
        val requiredRestore:Boolean,

        @Column(name = "repl_photo_url")
        val photoUrl:String,

        @Column(name = "repl_code")
        val code:String
)

fun ReplicaModel.toEntity() = Replica(
        id = id,
        state = state,
        requiredRestore = requiredRestore,
        photoUrl = photoUrl,
        code = code
)