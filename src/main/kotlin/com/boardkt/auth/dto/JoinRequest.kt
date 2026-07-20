package com.boardkt.auth.dto

import jakarta.validation.constraints.NotBlank

class JoinRequest(
    @NotBlank(message = "아이디는 비어있을 수 없습니다.")
    val userId: String,

    @NotBlank(message = "비밀번호는 비어있을 수 없습니다.")
    val password: String,

    @NotBlank(message = "닉네임은 비어있을 수 없습니다.")
    val nickname: String,
) {}