package com.boardkt.post.entity

import com.boardkt.global.entity.BaseEntity
import com.boardkt.user.entity.User
import jakarta.persistence.*

@Entity
@Table(name = "posts")
class Post protected constructor(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    var title: String,
    var content: String,
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    val user: User,
) : BaseEntity() {
    companion object {
        fun create(title: String, content: String, user: User) : Post {
            return Post(null, title, content, user)
        }
    }

    fun patch(title: String?, content: String?) {
        if (!title.isNullOrBlank()) {
            this.title = title
        }

        if (!content.isNullOrBlank()) {
            this.content = content
        }
    }

    fun validateOwner(userId: Long) {
        if (this.user.id != userId) throw RuntimeException("권한이 없습니다.")
    }
}
