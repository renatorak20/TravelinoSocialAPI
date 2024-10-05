package com.travel.travelapi.like.repository

import com.travel.travelapi.like.model.Like
import com.travel.travelapi.like.model.LikeType
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional

@Repository
interface LikeRepository : JpaRepository<Like, Long> {

    @Query("""
        SELECT l FROM Like l WHERE l.referenceId = :referenceId AND l.type = :type
    """)
    fun findByIdAndType(@Param("referenceId") referenceId: Long, @Param("type") type: LikeType): List<Like>

    @Modifying
    @Transactional
    @Query("DELETE FROM Like l WHERE l.id = :id")
    fun deleteByIdWithCount(id: Long): Int

}
