package com.boardkt.post.controller

import com.boardkt.global.dto.PaginatedResponse
import com.boardkt.post.dto.CreatePostRequest
import com.boardkt.post.dto.PatchPostRequest
import com.boardkt.post.dto.PostResponse
import com.boardkt.post.service.PostService
import jakarta.validation.Valid
import lombok.RequiredArgsConstructor
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.data.web.PageableDefault
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequiredArgsConstructor
class PostController(
    val postService: PostService
) {
    @PostMapping("/posts")
    fun createPost(@Valid @RequestBody request: CreatePostRequest): ResponseEntity<PostResponse> {
        val newPost = postService.createPost(request.title, request.content)
        return ResponseEntity.status(201).body(newPost)
    }

    @GetMapping("/posts/{id}")
    fun getSinglePost(@PathVariable id: Long): ResponseEntity<PostResponse> {
        val post = postService.getSinglePost(id)
        return ResponseEntity.ok(post)
    }

    @GetMapping("/posts")
    fun getAllPostsToPage(
        @PageableDefault(page = 0, size = 10, sort = ["createdAt"], direction = Sort.Direction.DESC)
        pageable: Pageable
    ): ResponseEntity<PaginatedResponse<PostResponse>> {
        val postsToPage = postService.getAllPostsToPage(pageable)
        return ResponseEntity.ok(postsToPage)
    }

    @PatchMapping("/posts/{id}")
    fun patchPost(
        @PathVariable id: Long,
        @RequestBody request: PatchPostRequest
    ): ResponseEntity<PostResponse> {
        val patchedPost = postService.patchPost(id, request.title, request.content)
        return ResponseEntity.ok(patchedPost)
    }

    @DeleteMapping("/posts/{id}")
    fun deletePost(@PathVariable id: Long) : ResponseEntity<Unit> {
        postService.deletePost(id)
        return ResponseEntity.noContent().build()
    }
}