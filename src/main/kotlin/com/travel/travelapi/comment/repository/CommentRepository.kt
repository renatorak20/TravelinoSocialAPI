package com.travel.travelapi.comment.repository

import com.travel.travelapi.comment.model.Comment
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface CommentRepository : JpaRepository<Comment, Long> {

    @Query("""
            SELECT c FROM Comment c WHERE c.postId = :postId
        """)
    fun getCommentsByPostId(@Param("postId") postId: Long): List<Comment>

}