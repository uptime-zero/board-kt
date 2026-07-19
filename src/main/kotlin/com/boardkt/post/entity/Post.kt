package com.boardkt.post.entity

import com.boardkt.global.entity.BaseEntity
import jakarta.persistence.*

@Entity
@Table(name = "posts")
class Post(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,
    var title: String,
    var content: String,
) : BaseEntity() {
    fun patch(title: String?, content: String?) {
        if (!title.isNullOrBlank()) {
            this.title = title
        }

        if (!content.isNullOrBlank()) {
            this.content = content
        }
    }
}
