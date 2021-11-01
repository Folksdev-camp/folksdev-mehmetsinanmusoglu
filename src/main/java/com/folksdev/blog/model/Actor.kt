package com.folksdev.blog.model

import org.hibernate.annotations.GenericGenerator
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
data class Actor @JvmOverloads constructor(
    @Id
    @Column(name = "actor_id")
    @GeneratedValue(generator = "mahmut")
    @GenericGenerator(name = "mahmut", strategy = "org.hibernate.id.UUIDGenerator")
    val id: String? = "",
    val name: String,
    val gender: Gender
) {

}

enum class Gender{
    MALE,FEMALE,UNKNOWN
}
