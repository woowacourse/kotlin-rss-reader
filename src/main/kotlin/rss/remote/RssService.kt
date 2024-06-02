package rss.remote

import rss.Blog

interface RssService {
    suspend fun fetchBlogPosts(url: List<String>): List<Blog>

    suspend fun fetchBlogPost(url: String): Blog
}
