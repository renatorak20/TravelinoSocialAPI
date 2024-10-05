package com.travel.travelapi.like.controller

import com.travel.travelapi.like.model.Like
import com.travel.travelapi.like.model.LikeType
import com.travel.travelapi.like.service.LikeService
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam

@Controller
@RequestMapping("/likes")
class LikeController(
    private val likeService: LikeService
) {

    @GetMapping
    fun getAll(@RequestParam("referenceType") referenceType: LikeType, @RequestParam("referenceId") referenceId: Long): ResponseEntity<List<Like>> {
        val likes = likeService.getAllByReferenceIdAndType(referenceType, referenceId)
        return ResponseEntity.ok(likes)
    }

    @PostMapping
    fun create(@RequestBody like: Like): ResponseEntity<Like> {
        val savedLike = likeService.createLike(like)
        return if (savedLike != null) {
            ResponseEntity.ok(savedLike)
        } else {
            ResponseEntity.internalServerError().build()
        }
    }

    @DeleteMapping
    fun delete(@RequestBody likeId: Long): ResponseEntity<Int> {
        val countOfDeletedLikes = likeService.deleteLike(likeId)
        return if (countOfDeletedLikes == 0) {
            ResponseEntity.notFound().build()
        } else {
            ResponseEntity.ok(countOfDeletedLikes)
        }
    }

}