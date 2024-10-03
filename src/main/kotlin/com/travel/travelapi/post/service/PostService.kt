package com.travel.travelapi.post.service

import com.travel.travelapi.post.model.Post
import com.travel.travelapi.post.repository.PostRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import java.util.*

@Service
class PostService(
    private val postRepository: PostRepository
) {

    fun getAllPosts(): List<Post> {
        return postRepository.findAll()
    }

    fun getPostById(id: Long): Optional<Post> {
        return postRepository.findById(id)
    }

    fun getPostsByPage(page: Int, size: Int): Page<Post> {
        val pageable = PageRequest.of(page, size)
        return postRepository.findAll(pageable)
    }

    fun createPost(post: Post): Post? {
        return try {
            postRepository.save(post)
        } catch (e: Exception) {
            null
        }
    }

    fun deletePosts() {
        postRepository.deleteAll()
    }

}