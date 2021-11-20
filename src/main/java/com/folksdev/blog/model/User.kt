package com.folksdev.blog.model

import org.hibernate.annotations.GenericGenerator
import javax.persistence.*

@Entity
@Table(name = "users")
data class User @JvmOverloads constructor(
    @Id
    @Column(name = "user_id")
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    val id: String? = "",
    val name: String,
    val email: String,
    val password: String,

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    val posts: Set<Post>? = HashSet(),

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    val comments: Set<Comment>? = HashSet()
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as User

        if (id != null && id != other.id) return false
        if (name != other.name) return false
        if (email != other.email) return false
        if (password != other.password) return false
        if (posts != other.posts) return false
        if (comments != other.comments) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id?.hashCode() ?: 0
        result = 31 * result + name.hashCode()
        result = 31 * result + email.hashCode()
        result = 31 * result + password.hashCode()
        result = 31 * result + (posts?.hashCode() ?: 0)
        result = 31 * result + (comments?.hashCode() ?: 0)
        return result
    }
}


