package rss

import kotlinx.coroutines.runBlocking
import rss.remote.RssServiceImpl

class RssController {
    fun start() =
        runBlocking {
            OutputView.showLoading()
            val blogs = fetchBlogPosts()
            val blogName = InputView.inputBlogName()
            val blog = selectBlog(blogName, blogs)
            OutputView.showPost(blog.recentBlogPosts(10))
            while (true) {
                val keyword = InputView.inputBlogKeyword()
                searchBlogPosts(keyword)
            }
        }

    private fun selectBlog(
        blogName: String,
        blogs: List<Blog>,
    ): Blog {
        var name = blogName
        while (blogs.none { it.isSameName(name) }) {
            println("해당 블로그를 찾을 수 없습니다. \n 다시 입력해주세요.")
            name = InputView.inputBlogName()
        }
        val blog = blogs.first { it.isSameName(name) }
        return blog
    }

    private suspend fun fetchBlogPosts(): List<Blog> {
        val posts = RssServiceImpl.fetchBlogPosts(DummyUrl)
        OutputView.showBlogIntroduction(posts)
        return posts
    }

    private suspend fun searchBlogPosts(keyword: String) {
        OutputView.showLoading()
        val posts = RssServiceImpl.fetchBlogPosts(DummyUrl).toBlogPost()
        val searchResult = posts.findBlogPostsByTitle(keyword).recentBlogPosts(10)
        OutputView.showSearchResult(searchResult.items)
    }

    private fun List<Blog>.toBlogPost(): BlogPosts = map { it.posts.items }.flatten().let(::BlogPosts)

    companion object {
        val DummyUrl: List<String> =
            listOf(
                "https://techblog.woowahan.com/feed/",
                "https://v2.velog.io/rss/murjune",
                "https://www.javacodegeeks.com/feed",
            )
    }
}
