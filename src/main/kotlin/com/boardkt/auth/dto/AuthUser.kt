package com.boardkt.auth.dto

import com.boardkt.user.entity.User

class AuthUser(
    val id: Long,
    val nickname: String,
) {
    companion object {
        fun from(user: User) : AuthUser {
            return AuthUser(user.id!!, user.nickname)
        }
    }
}