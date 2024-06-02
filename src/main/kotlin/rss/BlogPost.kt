package rss

data class BlogPost(
    val name: String,
    val homeLink: String,
    val description: String,
    val posts: List<BlogPostItem>,
) {
    fun recentBlogPosts(size: Int): List<BlogPostItem> = posts.sortedByDescending { it.pubDate }.take(size)

    fun findBlogPostsByTitle(title: String): List<BlogPostItem> = posts.filter { it.title.contains(title.trim()) }
}
