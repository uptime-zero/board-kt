package com.boardkt.auth.controller

import com.boardkt.auth.dto.AuthUser
import com.boardkt.auth.dto.JoinRequest
import com.boardkt.auth.service.AuthService
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/auth")
class AuthController(
    private val authService: AuthService
) {

    @PostMapping("/join")
    fun join(
        @Valid @RequestBody request: JoinRequest
    ): ResponseEntity<AuthUser> {
        val authUser = authService.join(request.userId, request.password, request.nickname)
        return ResponseEntity.ok(authUser)
    }

}