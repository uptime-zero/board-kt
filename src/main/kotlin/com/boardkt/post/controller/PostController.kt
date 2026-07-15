package com.boardkt.post.controller

import com.boardkt.post.dto.CreatePostRequest
import com.boardkt.post.dto.PostResponse
import com.boardkt.post.service.PostService
import jakarta.validation.Valid
import lombok.RequiredArgsConstructor
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

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
}