package com.folksdev.blog.model

import org.hibernate.annotations.GenericGenerator
import javax.persistence.*

@Entity
@Table(name = "categories")
data class Category @JvmOverloads constructor(
    @Id
    @Column(name = "category_id")
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    val id: String? = "",
    val name: String,

    @ManyToMany(mappedBy = "categories", fetch = FetchType.LAZY)
    val posts: Set<Post>? = HashSet()
){
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Category

        if (id != other.id) return false
        if (name != other.name) return false
        if (posts != other.posts) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id?.hashCode() ?: 0
        result = 31 * result + name.hashCode()
        result = 31 * result + (posts?.hashCode() ?: 0)
        return result
    }
}
