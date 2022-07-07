package io.github.t3m8ch.models

data class Post(
    val id: Int,
    val text: String,
    val views: Int,
    val likes: Int,
    val dislikes: Int,
)
