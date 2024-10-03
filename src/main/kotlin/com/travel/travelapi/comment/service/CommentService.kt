package com.travel.travelapi.comment.service

import com.travel.travelapi.comment.model.Comment
import com.travel.travelapi.post.model.Post
import com.travel.travelapi.comment.repository.CommentRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class CommentService(
    private val commentRepository: CommentRepository
) {

    fun getCommentsByPostId(postId: Long): List<Comment> {
        return commentRepository.getCommentsByPostId(postId)
    }

    fun createComment(comment: Comment): Comment? {
        return try {
            commentRepository.save(comment)
        } catch (e: Exception) {
            null
        }
    }

}