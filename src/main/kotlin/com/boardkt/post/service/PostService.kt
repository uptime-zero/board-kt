package com.boardkt.post.service

import com.boardkt.global.dto.PaginatedResponse
import com.boardkt.post.dto.PostResponse
import com.boardkt.post.entity.Post
import com.boardkt.post.repository.PostJpaRepository
import lombok.RequiredArgsConstructor
import lombok.extern.slf4j.Slf4j
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.findByIdOrNull
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

    @Transactional(readOnly = true)
    fun getSinglePost(id: Long): PostResponse {
        val getPost = postJpaRepository.findByIdOrNull(id) ?: throw RuntimeException("해당 게시글을 찾을 수 없습니다.")
        return PostResponse(getPost.id!!, getPost.title, getPost.content)
    }

    @Transactional(readOnly = true)
    fun getAllPostsToPage(pageable: Pageable): PaginatedResponse<PostResponse> {
        return postJpaRepository.findAll(pageable)
            .let { page ->
                PaginatedResponse.from(
                    page.map { post ->
                        PostResponse(post.id!!, post.title, post.content)
                    })
            }
    }

    @Transactional
    fun patchPost(id: Long, title: String?, content: String?): PostResponse {
        if (title.isNullOrBlank() && content.isNullOrBlank()) {
            throw RuntimeException("수정할 제목 또는 본문 중 최소 하나는 입력해야 합니다.")
        }

        val getPost = postJpaRepository.findByIdOrNull(id) ?: throw RuntimeException("해당 게시글을 찾을 수 없습니다.")
        getPost.patch(title, content)

        return PostResponse(getPost.id!!, getPost.title, getPost.content)
    }
}