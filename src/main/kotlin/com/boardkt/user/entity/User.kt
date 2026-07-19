package com.boardkt.user.entity

import com.boardkt.global.entity.BaseEntity
import jakarta.persistence.*

@Entity
@Table(name = "users")
class User protected constructor(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(unique = true, nullable = false)
    val userId: String,

    @Column(nullable = false)
    var password: String,

    @Column(unique = true, nullable = false)
    var nickname: String,
) : BaseEntity() {
    companion object {
        fun create(userId: String, password: String, nickname: String) : User {
            return User(null, userId, password, nickname)
        }
    }
}