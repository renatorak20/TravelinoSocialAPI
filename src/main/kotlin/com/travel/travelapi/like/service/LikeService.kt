package com.travel.travelapi.like.service

import com.travel.travelapi.comment.service.CommentService
import com.travel.travelapi.like.model.Like
import com.travel.travelapi.like.model.LikeType
import com.travel.travelapi.like.repository.LikeRepository
import com.travel.travelapi.post.service.PostService
import jakarta.persistence.EntityNotFoundException
import org.springframework.stereotype.Service

@Service
class LikeService(
    private val likeRepository: LikeRepository,
    private val postService: PostService,
    private val commentService: CommentService
) {

    fun getAllByReferenceIdAndType(type: LikeType, id: Long): List<Like> {
        return likeRepository.findByIdAndType(id, type)
    }

    fun getCountByReferenceIdAndType(type: LikeType, id: Long): Int {
        return getAllByReferenceIdAndType(type, id).size
    }

    fun createLike(like: Like): Like? {
        if (like.referenceId == null) throw IllegalArgumentException("Referenced object's id must be present!")

        val referenceId = like.referenceId
        val referenceExists = when (like.type) {
            LikeType.POST -> postService.postExists(referenceId)
            else -> commentService.commentExists(referenceId)
        }

        if (referenceExists) {
            return try {
                likeRepository.save(like)
            } catch (e: Exception) {
                throw IllegalArgumentException("There's been a problem while saving a like.")
            }
        } else {
            throw EntityNotFoundException("Referenced object of type ${like.type} is not found!")
        }

    }

    fun deleteLike(likeId: Long): Int {
        return likeRepository.deleteByIdWithCount(likeId)
    }

}