package com.virgen.peregrina.demo.data.entity


import java.time.LocalDateTime
import javax.persistence.*


@Entity
@Table(name = "blog_posts")
class BlogPost {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    var id: Long? = null

    @Column(name = "title")
    var title: String? = null

    @Column(name = "content", length = 10000)
    var content: String? = null

    @Column(name = "image_url")
    var imageUrl: String? = null

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    var user: User? = null

    @Column(name = "created_at")
    var createdAt: LocalDateTime? = null

    @Column(name = "status")
    var status: Boolean? = null

    // Constructor vacío y completo
    constructor()

    constructor(id: Long?, title: String?, content: String?, imageUrl: String?, user: User?, createdAt: LocalDateTime?) {
        this.id = id
        this.title = title
        this.content = content
        this.imageUrl = imageUrl
        this.user = user
        this.createdAt = createdAt
    }
}
