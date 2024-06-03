package model

import java.time.LocalDateTime

data class Article(
    val title: String,
    val link: String,
    val publishedDate: LocalDateTime,
    val description: String,
)
