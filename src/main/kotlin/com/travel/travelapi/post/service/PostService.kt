package com.travel.travelapi.post.service

import com.travel.travelapi.like.model.LikeType
import com.travel.travelapi.like.service.LikeService
import com.travel.travelapi.post.model.Post
import com.travel.travelapi.post.repository.PostRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service
import java.util.*

@Service
class PostService(
    private val postRepository: PostRepository,
    private val likeService: LikeService
) {

    fun getAllPosts(): List<Post> {
        return postRepository.findAll().map { it.populateLikesCount() }
    }

    fun getPostById(id: Long): Optional<Post> {
        return postRepository.findById(id).map { it.populateLikesCount() }
    }

    fun getPostsByPage(page: Int, size: Int): Page<Post> {
        val pageable = PageRequest.of(page, size)
        return postRepository.findAll(pageable).map { it.populateLikesCount() }
    }

    fun postExists(id: Long): Boolean {
        return postRepository.existsById(id)
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

    fun Post.populateLikesCount(): Post {
        val likesCount = likeService.getCountByReferenceIdAndType(LikeType.POST, this.id!!)
        return this.copy(likes = likesCount)
    }

}