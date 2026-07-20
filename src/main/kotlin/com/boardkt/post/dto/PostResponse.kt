package com.boardkt.post.dto

import com.boardkt.auth.dto.AuthUser
import com.boardkt.post.entity.Post

class PostResponse(
    val id: Long,
    val title: String,
    val content: String,
    val createdAt: String,
    val updatedAt: String,
    val author: AuthUser
) {
    companion object {
        fun from(post: Post, authUser: AuthUser): PostResponse {
            return PostResponse(
                post.id!!,
                post.title,
                post.content,
                post.createdAt.toString(),
                post.updatedAt.toString(),
                authUser
            )
        }
    }
}