package com.boardkt.post.service

import com.boardkt.post.dto.PostResponse
import com.boardkt.post.entity.Post
import com.boardkt.post.repository.PostJpaRepository
import lombok.RequiredArgsConstructor
import lombok.extern.slf4j.Slf4j
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Slf4j
@Service
@RequiredArgsConstructor
class PostService(
    val postJpaRepository: PostJpaRepository
) {
    @Transactional
    fun createPost(title: String, content: String): PostResponse {
        val newPost = postJpaRepository.save(Post(title = title, content = content))
        return PostResponse(newPost.id!!, newPost.title, newPost.content)
    }
}