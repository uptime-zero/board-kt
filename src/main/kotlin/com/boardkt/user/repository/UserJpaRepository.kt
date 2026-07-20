package com.boardkt.user.repository

import com.boardkt.user.entity.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserJpaRepository : JpaRepository<User, Long> {
    fun existsByUserId(userId: String): Boolean
    fun existsByNickname(nickname: String): Boolean
}