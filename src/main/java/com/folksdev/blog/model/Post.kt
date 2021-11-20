package com.folksdev.blog.model

import org.hibernate.annotations.GenericGenerator
import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "posts")
data class Post @JvmOverloads constructor(
    @Id
    @Column(name = "post_id")
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    val id: String? = "",
    val title: String,
    val context: String,

    @Column(name = "created_at")
    val createDate: LocalDateTime = LocalDateTime.now(),

    @Column(name = "updated_at")
    val updateDate: LocalDateTime = LocalDateTime.now(),

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    val user: User,

    @OneToMany(mappedBy = "post", fetch = FetchType.LAZY)
    val comment: Set<Comment>? = HashSet(),

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "post_categories",
        joinColumns = [JoinColumn(name = "post_id", referencedColumnName = "post_id")],
        inverseJoinColumns = [JoinColumn(name = "category_id", referencedColumnName = "category_id")]
    )
    val categories: Set<Category>?

) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Post

        if (id != null && id != other.id) return false
        if (context != other.context) return false
        if (createDate != other.createDate) return false
        if (updateDate != other.updateDate) return false
        if (createDate != other.createDate) return false
        if (comment != other.comment) return false
        if (createDate != other.createDate) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id?.hashCode() ?: 0
        result = 31 * result + context.hashCode()
        result = 31 * result + createDate.hashCode()
        result = 31 * result + updateDate.hashCode()
        result = 31 * result + createDate.hashCode()
        result = 31 * result + (comment?.hashCode() ?: 0)
        result = 31 * result + (createDate?.hashCode() ?: 0)
        return result
    }
}
