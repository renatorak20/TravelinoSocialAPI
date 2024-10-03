package com.travel.travelapi.comment.model

import com.travel.travelapi.auth.User
import com.travel.travelapi.post.model.Post
import jakarta.persistence.*
import java.time.Instant

@Entity
data class Comment(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(nullable = false)
    val text: String = "",

    @ManyToOne
    @JoinColumn(name = "user_id")
    var user: User? = null,

    @ManyToOne
    @JoinColumn(name = "post_id")
    var post: Post? = null,

    @Column(nullable = true)
    val timestamp: Long = Instant.now().toEpochMilli()
)
