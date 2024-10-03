package com.travel.travelapi.comment.repository

import com.travel.travelapi.comment.model.Comment
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.query.Param

interface CommentRepository : CrudRepository<Comment, Long> {

    @Query("""
            SELECT c.id, c.text, c.user, c.post, c.timestamp FROM Comment c WHERE c.id = :postId
        """)
    fun getCommentsByPostId(@Param("postId") postId: Long): List<Comment>

}