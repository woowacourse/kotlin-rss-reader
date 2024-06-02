package rss

import java.time.LocalDateTime

private val defaultDateTime = LocalDateTime.of(2024, 6, 3, 0, 0)

fun fakeBlogPostItem(
    title: String = "title",
    link: String = "link",
    pubDate: LocalDateTime = defaultDateTime,
    content: String = "content",
) = BlogPostItem(
    title = title,
    link = link,
    pubDate = pubDate,
    content = content,
)

fun fakeBlogPost(vararg localDateTime: LocalDateTime) =
    fakeBlogPost(
        posts = localDateTime.map { fakeBlogPostItem(pubDate = it) },
    )

fun fakeBlogPost(vararg posts: BlogPostItem = emptyArray()) = fakeBlogPost(posts = posts.toList())

fun fakeBlogPost(
    name: String = "name",
    homeLink: String = "homeLink",
    description: String = "description",
    posts: List<BlogPostItem> = listOf(fakeBlogPostItem()),
) = Blog(
    blogName = name,
    homeLink = homeLink,
    description = description,
    posts = posts.let(::BlogPosts),
)
