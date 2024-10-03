package com.travel.travelapi.comment.controller

import com.travel.travelapi.comment.model.Comment
import com.travel.travelapi.post.model.Post
import com.travel.travelapi.comment.service.CommentService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/post/{postId}/comment")
class CommentController(
    private val commentService: CommentService
) {

    @GetMapping
    fun getCommentsByPostId(@PathVariable("postId") postId: Long): ResponseEntity<List<Comment>> {
        val comments = commentService.getCommentsByPostId(postId)
        return ResponseEntity.ok(comments)
    }

    @PostMapping
    fun createComment(@RequestBody request: Comment): ResponseEntity<Comment?> {
        val createdComment = commentService.createComment(request)
        return if (createdComment != null) {
            ResponseEntity.ok(createdComment)
        } else {
            ResponseEntity.internalServerError().build<Comment?>()
        }
    }

}