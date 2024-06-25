package com.virgen.peregrina.demo.data.model


import java.time.LocalDateTime;

data class BlogPostDTO(
        var id: Long? = null,
        var title: String? = null,
        var content: String? = null,
        var imageUrl: String? = null,
        var userId: Long? = null,
        var createdAt:LocalDateTime? = null,
        var autor: String? = null, // Propiedad para el nombre del autor
        var email: String? = null,
        var status: Boolean? = null


)
