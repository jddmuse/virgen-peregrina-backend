package com.virgen.peregrina.demo.service.implement

import com.virgen.peregrina.demo.data.converter.BlogPostConverter
import com.virgen.peregrina.demo.data.entity.BlogPost
import com.virgen.peregrina.demo.data.model.BlogPostDTO
import com.virgen.peregrina.demo.repository.BlogPostRepository
import com.virgen.peregrina.demo.repository.UserRepository
import com.virgen.peregrina.demo.service.BlogPostService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.stream.Collectors

@Service
class BlogPostServiceImpl : BlogPostService {

    @Autowired
    private lateinit var blogPostRepository: BlogPostRepository

    @Autowired
    private lateinit var blogPostConverter: BlogPostConverter

    @Autowired
    private lateinit var userRepository: UserRepository

    override fun createBlogPost(blogPostDTO: BlogPostDTO): BlogPostDTO {
        val blogPost = blogPostConverter.toEntity(blogPostDTO)
        val savedBlogPost = blogPostRepository.save(blogPost)
        return blogPostConverter.toDTO(savedBlogPost)
    }

    override fun getBlogPostById(id: Long): BlogPostDTO {
        val blogPost = blogPostRepository.findById(id)
            .orElseThrow { RuntimeException("Blog post not found") }
        return blogPostConverter.toDTO(blogPost)
    }

    override fun getAllBlogPosts(): List<BlogPostDTO> {
        return blogPostRepository.findAll().stream()
            .map { blogPost -> blogPostConverter.toDTO(blogPost) }
            .collect(Collectors.toList())
    }

    override fun updateBlogPost(id: Long, blogPostDTO: BlogPostDTO): BlogPostDTO {
        val blogPost = blogPostRepository.findById(id)
            .orElseThrow { RuntimeException("Blog post not found") }

        // Update properties from DTO
        blogPost.title = blogPostDTO.title
        blogPost.content = blogPostDTO.content
        blogPost.imageUrl = blogPostDTO.imageUrl
        blogPost.status = blogPostDTO.status
        blogPost.user = blogPostDTO.userId?.let {
            userRepository.findById(it)
                .orElseThrow { RuntimeException("User not found") }
        }
        blogPost.createdAt = blogPostDTO.createdAt

        // Save updated blog post
        val updatedBlogPost = blogPostRepository.save(blogPost)
        return blogPostConverter.toDTO(updatedBlogPost)
    }

    override fun deleteBlogPost(id: Long) {
        blogPostRepository.deleteById(id)
    }
}
