package com.travel.travelapi.post.controller

import com.travel.travelapi.post.model.Post
import com.travel.travelapi.post.model.PageData
import com.travel.travelapi.post.model.PostPagedResponse
import com.travel.travelapi.post.service.PostService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
@RequestMapping("/post")
class PostController(
    private val postService: PostService
) {

    @GetMapping
    fun getAll(): ResponseEntity<List<Post>> {
        val allPosts = postService.getAllPosts()
        return ResponseEntity.ok(allPosts)
    }

    @GetMapping("/{id}")
    fun getById(@PathVariable("id") id: Long): ResponseEntity<Optional<Post>> {
        val post = postService.getPostById(id)
        return if (post.isPresent) ResponseEntity.ok(post) else ResponseEntity.notFound().build()
    }

    @GetMapping(params = ["page", "size"])
    fun getByPage(@RequestParam("page") page: Int = 0, @RequestParam("size") size: Int = 5): PostPagedResponse {
        val pageResponse = postService.getPostsByPage(page, size)
        return PostPagedResponse(
            posts = pageResponse.content,
            page = PageData(
                pageResponse.size,
                pageResponse.totalElements,
                pageResponse.totalPages,
                pageResponse.number
            )
        )
    }

    @PostMapping
    fun createPost(@RequestBody request: Post): ResponseEntity<Post?> {
        val createdPost = postService.createPost(request)
        return if (createdPost != null) {
            ResponseEntity.ok(createdPost)
        } else {
            ResponseEntity.internalServerError().build<Post?>()
        }
    }

    @DeleteMapping
    fun deletePosts(): ResponseEntity<*> {
        postService.deletePosts()
        return ResponseEntity.ok(true)
    }

}