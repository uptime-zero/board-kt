package com.boardkt.post.repository

import com.boardkt.post.entity.Post
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository

interface PostJpaRepository : JpaRepository<Post, Long> {
    override fun findAll(pageable: Pageable): Page<Post>
}