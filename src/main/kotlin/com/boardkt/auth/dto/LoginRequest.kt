package com.boardkt.auth.dto

import jakarta.validation.constraints.NotBlank

class LoginRequest(
    @NotBlank(message = "공백일 수 없습니다.")
    val userId: String,

    @NotBlank(message = "공백일 수 없습니다.")
    val password: String,
) {
}