package com.virgen.peregrina.demo.data.converter

import com.virgen.peregrina.demo.data.entity.BlogPost
import com.virgen.peregrina.demo.data.model.BlogPostDTO
import com.virgen.peregrina.demo.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class BlogPostConverter {

    @Autowired
    private lateinit var userRepository: UserRepository

    fun toDTO(blogPost: BlogPost): BlogPostDTO {
        return BlogPostDTO().apply {
            id = blogPost.id
            title = blogPost.title
            content = blogPost.content
            userId = blogPost.user?.id
            createdAt = blogPost.createdAt
            imageUrl = blogPost.imageUrl
            status = blogPost.status
            // Obtener el nombre del autor desde UserRepository si existe el user
            userId?.let { userId ->
                userRepository.findById(userId)?.ifPresent { user ->
                    autor = user.name + " "+ user.lastName
                    email = user.email
                }
            }
        }
    }

    fun toEntity(dto: BlogPostDTO): BlogPost {
        return BlogPost().apply {
            id = dto.id
            title = dto.title
            content = dto.content
            status = dto.status
            user = userRepository.findById(dto.userId!!)
                .orElseThrow { RuntimeException("User not found") }
            createdAt = dto.createdAt
            dto.imageUrl?.let { imageUrl = it }
        }
    }
}
