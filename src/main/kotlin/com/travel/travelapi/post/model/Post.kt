package com.travel.travelapi.post.model

import com.travel.travelapi.auth.User
import jakarta.persistence.*
import java.math.BigDecimal
import java.time.Instant

@Entity
data class Post(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(nullable = false)
    val description: String = "",

    @Column(nullable = false)
    var userId: Long,

    @Column(name = "image_url")
    val imageUrl: String? = null,

    @Column(precision = 9, scale = 6)
    val latitude: BigDecimal? = null,

    @Column(precision = 9, scale = 6)
    val longitude: BigDecimal? = null,

    @Column(nullable = true)
    val timestamp: Long? = Instant.now().toEpochMilli(),

    @Column(nullable = true)
    val city: String? = ""
)
