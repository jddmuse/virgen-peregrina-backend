package com.virgen.peregrina.demo.service

import com.virgen.peregrina.demo.data.model.BlogPostDTO

interface BlogPostService {

    fun createBlogPost(blogPostDTO: BlogPostDTO): BlogPostDTO

    fun getBlogPostById(id: Long): BlogPostDTO

    fun getAllBlogPosts(): List<BlogPostDTO>

    fun updateBlogPost(id: Long, blogPostDTO: BlogPostDTO): BlogPostDTO

    fun deleteBlogPost(id: Long)
}
