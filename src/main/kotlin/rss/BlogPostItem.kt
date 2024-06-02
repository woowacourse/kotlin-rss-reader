package rss

import java.time.LocalDateTime

data class BlogPostItem(
    val title: String,
    val link: String,
    val pubDate: LocalDateTime,
    val content: String,
)
