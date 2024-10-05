package com.travel.travelapi.comment.model

import jakarta.persistence.*
import java.time.Instant

@Entity
data class Comment(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(nullable = true)
    val text: String? = null,

    @Column(nullable = true)
    val userId: Long? = null,

    @Column(nullable = true)
    val postId: Long? = null,

    @Column(nullable = true)
    val timestamp: Long? = Instant.now().toEpochMilli(),

    @Column(nullable = true)
    val likes: Int? = 0
)
