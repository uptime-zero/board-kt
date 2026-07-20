package com.boardkt.auth.controller

import com.boardkt.auth.dto.AuthUser
import com.boardkt.auth.dto.JoinRequest
import com.boardkt.auth.dto.LoginRequest
import com.boardkt.auth.service.AuthService
import jakarta.servlet.http.HttpServletRequest
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

    @PostMapping("/login")
    fun login(
        @Valid @RequestBody request: LoginRequest,
        httpRequest: HttpServletRequest,
    ): ResponseEntity<AuthUser> {
        val authUser = authService.login(request.userId, request.password)

        httpRequest.getSession(false)?.invalidate()
        val newSession = httpRequest.getSession(true)
        newSession.setAttribute("auth", authUser)
        newSession.maxInactiveInterval = 1800

        return ResponseEntity.ok(authUser)
    }

    @PostMapping("/logout")
    fun logout(httpRequest: HttpServletRequest) : ResponseEntity<Unit> {
        httpRequest.getSession(false)?.invalidate()
        return ResponseEntity.noContent().build()
    }
}