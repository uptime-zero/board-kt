package com.boardkt.post.repository

import com.boardkt.post.entity.Post
import org.springframework.data.jpa.repository.JpaRepository

interface PostJpaRepository: JpaRepository<Post, Long> {

}