package com.virgen.peregrina.demo.controller


import com.virgen.peregrina.demo.data.model.BlogPostDTO
import com.virgen.peregrina.demo.service.BlogPostService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@CrossOrigin(origins = ["http://127.0.0.1:5500"])  // Permite solicitudes desde http://127.0.0.1:5500
@RestController
@RequestMapping("/blogposts")
class BlogPostController {

    @Autowired
    private lateinit var blogPostService: BlogPostService

    @PostMapping("/create")
    fun createBlogPost(@RequestBody blogPostDTO: BlogPostDTO): ResponseEntity<BlogPostDTO> {
        return try {
            val createdBlogPost = blogPostService.createBlogPost(blogPostDTO)
            ResponseEntity(createdBlogPost, HttpStatus.CREATED)
        } catch (e: Exception) {
            ResponseEntity(null, HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

    @GetMapping("/{id}")
    fun getBlogPostById(@PathVariable id: Long): ResponseEntity<BlogPostDTO> {
        val blogPostDTO = blogPostService.getBlogPostById(id)
        return ResponseEntity(blogPostDTO, HttpStatus.OK)
    }

    @GetMapping
    fun getAllBlogPosts(): ResponseEntity<List<BlogPostDTO>> {
        val blogPosts = blogPostService.getAllBlogPosts()
        return ResponseEntity(blogPosts, HttpStatus.OK)
    }

    @PutMapping("/{id}")
    fun updateBlogPost(@PathVariable id: Long, @RequestBody blogPostDTO: BlogPostDTO): ResponseEntity<BlogPostDTO> {
        val updatedBlogPost = blogPostService.updateBlogPost(id, blogPostDTO)
        return ResponseEntity(updatedBlogPost, HttpStatus.OK)
    }

    @DeleteMapping("/{id}")
    fun deleteBlogPost(@PathVariable id: Long): ResponseEntity<Void> {
        blogPostService.deleteBlogPost(id)
        return ResponseEntity(HttpStatus.NO_CONTENT)
    }
}
