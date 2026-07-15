package com.boardkt.post.dto

import jakarta.validation.constraints.NotBlank

class CreatePostRequest(
    @NotBlank(message = "제목은 비어있을 수 없습니다.")
    val title: String,

    @NotBlank(message = "본문은 비어있을 수 없습니다.")
    val content: String,
) {
}