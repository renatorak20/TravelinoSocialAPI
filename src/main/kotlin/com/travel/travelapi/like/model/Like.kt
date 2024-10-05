package com.travel.travelapi.like.model

import jakarta.persistence.*
import java.time.Instant

@Entity
@Table(name = "likes")
data class Like(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(nullable = false)
    val type: LikeType? = null,

    @Column(nullable = false)
    val userId: Long? = null,

    @Column(nullable = false)
    val referenceId: Long? = null,

    @Column(nullable = true)
    val timestamp: Long? = Instant.now().toEpochMilli()
)
