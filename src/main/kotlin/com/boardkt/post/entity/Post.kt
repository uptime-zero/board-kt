package com.boardkt.post.entity

import com.boardkt.global.entity.BaseEntity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

data class Post(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,
    var title: String,
    var content: String,
): BaseEntity()
