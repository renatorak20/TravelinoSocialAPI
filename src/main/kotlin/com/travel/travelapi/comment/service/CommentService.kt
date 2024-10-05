package com.travel.travelapi.comment.service

import com.travel.travelapi.comment.model.Comment
import com.travel.travelapi.post.model.Post
import com.travel.travelapi.comment.repository.CommentRepository
import com.travel.travelapi.like.model.LikeType
import com.travel.travelapi.like.service.LikeService
import com.travel.travelapi.post.service.PostService
import jakarta.persistence.EntityNotFoundException
import org.springframework.stereotype.Service
import java.util.*

@Service
class CommentService(
    private val commentRepository: CommentRepository,
    private val postService: PostService,
    private val likeService: LikeService
) {

    fun getCommentsByPostId(postId: Long): List<Comment> {
        return commentRepository.getCommentsByPostId(postId).map { it.populateLikesCount() }
    }

    fun commentExists(id: Long): Boolean {
        return commentRepository.existsById(id)
    }

    fun createComment(comment: Comment): Comment {
        val postExists = postService.postExists(comment.postId!!)
        return if (postExists) {
            try {
                commentRepository.save(comment)
            } catch (e: Exception) {
                throw IllegalArgumentException("There's been a problem while saving a comment.")
            }
        } else {
            throw EntityNotFoundException("Unable to find post.")
        }
    }

    fun Comment.populateLikesCount(): Comment {
        val likesCount = likeService.getCountByReferenceIdAndType(LikeType.COMMENT, this.id!!)
        return this.copy(likes = likesCount)
    }

}