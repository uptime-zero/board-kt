package com.boardkt

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaAuditing

@EnableJpaAuditing
@SpringBootApplication
class BoardKtApplication

fun main(args: Array<String>) {
    runApplication<BoardKtApplication>(*args)
}
