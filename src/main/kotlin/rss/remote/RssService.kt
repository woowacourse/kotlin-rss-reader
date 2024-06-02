package rss.remote

import rss.BlogPost

interface RssService {

    suspend fun fetchBlogPosts(url: List<String>): List<BlogPost>

    suspend fun fetchBlogPost(url: String): BlogPost
}
