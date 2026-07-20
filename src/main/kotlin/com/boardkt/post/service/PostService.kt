package com.boardkt.post.service

import com.boardkt.auth.dto.AuthUser
import com.boardkt.global.dto.PaginatedResponse
import com.boardkt.post.dto.PostResponse
import com.boardkt.post.entity.Post
import com.boardkt.post.repository.PostJpaRepository
import com.boardkt.user.repository.UserJpaRepository
import lombok.RequiredArgsConstructor
import lombok.extern.slf4j.Slf4j
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class PostService(
    private val postJpaRepository: PostJpaRepository,
    private val userJpaRepository: UserJpaRepository,
) {
    @Transactional
    fun createPost(title: String, content: String, authUser: AuthUser): PostResponse {
        val user = userJpaRepository.findByIdOrNull(authUser.id)
            ?: throw RuntimeException("유저를 찾을 수 없습니다.")

        val newPost = postJpaRepository.save(Post.create(title = title, content = content, user = user))
        return PostResponse.from(newPost, authUser)
    }

    @Transactional(readOnly = true)
    fun getSinglePost(id: Long): PostResponse {
        val post = postJpaRepository.findByIdOrNull(id)
            ?: throw RuntimeException("게시글을 찾을 수 없습니다.")
        return PostResponse.from(post, AuthUser.from(post.user))
    }

    @Transactional(readOnly = true)
    fun getAllPostsToPage(pageable: Pageable): PaginatedResponse<PostResponse> {
        val page = postJpaRepository.findAll(pageable)
        return PaginatedResponse.from(page.map { PostResponse.from(it, AuthUser.from(it.user)) })
    }

    @Transactional
    fun patchPost(id: Long, title: String?, content: String?, authUser: AuthUser): PostResponse {
        if (title.isNullOrBlank() && content.isNullOrBlank()) {
            throw RuntimeException("수정할 제목 또는 본문 중 최소 하나는 입력해야 합니다.")
        }

        val post = postJpaRepository.findByIdOrNull(id)
            ?: throw RuntimeException("게시글을 찾을 수 없습니다.")

        post.validateOwner(authUser.id)
        post.patch(title, content)

        return PostResponse.from(post, authUser)
    }

    @Transactional
    fun deletePost(id: Long, authUser: AuthUser) {
        val post = postJpaRepository.findByIdOrNull(id)
            ?: throw RuntimeException("게시글을 찾을 수 없습니다.")

        post.validateOwner(authUser.id)
        postJpaRepository.delete(post)
    }
}