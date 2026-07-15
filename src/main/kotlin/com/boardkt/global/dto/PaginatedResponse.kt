package com.boardkt.global.dto

import org.springframework.data.domain.Page

class PaginatedResponse<T>(
    val data: List<T>,
    val currentPage: Int,
    val totalPages: Int,
    val totalItems: Long,
    val pageSize: Int,
    val hasNext: Boolean,
    val hasPrevious: Boolean,
) {
    companion object {
        fun <T : Any> from(page: Page<T>): PaginatedResponse<T> = PaginatedResponse(
            data = page.content,
            currentPage = page.number,
            totalPages = page.totalPages,
            totalItems = page.totalElements,
            pageSize = page.size,
            hasNext = page.hasNext(),
            hasPrevious = page.hasPrevious(),
        )
    }
}