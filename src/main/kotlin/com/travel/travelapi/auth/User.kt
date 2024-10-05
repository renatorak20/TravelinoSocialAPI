package com.travel.travelapi.auth

import jakarta.persistence.*

@Entity
@Table(name = "users")
data class User (

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(nullable = false)
    val username: String,

    @Column(nullable = false)
    val fullName: String,

    @Column(nullable = false)
    val email: String,

    @Column(nullable = true)
    val photoUrl: String? = null,

    @Column(nullable = true)
    val bio: String? = null

)