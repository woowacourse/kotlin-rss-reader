package rss

data class Blog(
    val blogName: String,
    val homeLink: String,
    val description: String,
    val posts: BlogPosts,
) {
    fun isSameName(name: String): Boolean = blogName == name

    fun recentBlogPosts(size: Int): BlogPosts = posts.recentBlogPosts(size)

    fun findBlogPostsByTitle(title: String): BlogPosts = posts.findBlogPostsByTitle(title)
}

@JvmInline
value class BlogPosts(
    val items: List<BlogPostItem>,
) {
    fun recentBlogPosts(size: Int): BlogPosts = items.sortedByDescending { it.pubDate }.take(size).let(::BlogPosts)

    fun findBlogPostsByTitle(title: String): BlogPosts = items.filter { it.title.contains(title.trim()) }.let(::BlogPosts)
}
