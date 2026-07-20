package com.boardkt.auth.service

import com.boardkt.auth.dto.AuthUser
import com.boardkt.user.entity.User
import com.boardkt.user.repository.UserJpaRepository
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class AuthService(
    private val userJpaRepository: UserJpaRepository,
    private val passwordEncoder: PasswordEncoder
) {

    @Transactional
    fun join(userId: String, password: String, nickname: String): AuthUser {
        if (userJpaRepository.existsByUserId(userId)) {
            throw RuntimeException("이미 존재하는 아이디입니다.")
        }

        if (userJpaRepository.existsByNickname(nickname)) {
            throw RuntimeException("이미 존재하는 닉네임입니다.")
        }

        val newUser = userJpaRepository.save(User.create(userId, passwordEncoder.encode(password)!!, nickname))

        return AuthUser(newUser.id!!, newUser.nickname)
    }

}