package com.travel.travelapi.post.repository

import com.travel.travelapi.post.model.Post
import org.springframework.data.jpa.repository.JpaRepository

interface PostRepository : JpaRepository<Post, Long> {
}