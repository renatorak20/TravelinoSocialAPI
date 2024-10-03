package com.travel.travelapi.post.model

data class PostPagedResponse(
    val posts: List<Post>,
    val page: PageData
)

data class PageData(
    val size: Int,
    val totalElements: Long,
    val totalPages: Int,
    val pageNumber: Int
)
