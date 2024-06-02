package rss

object OutputView {
    private const val DATE_FORMAT_PATTERN = "yyyy-MM-dd HH:mm:ss"
    private const val PREVIEW_CONTENT_LENGTH = 100

    fun showLoading() {
        println("불러오는 중입니다...")
    }

    fun showBlogIntroduction(posts: List<Blog>) {
        if (posts.isEmpty()) {
            println("블로그 글이 없습니다.")
            return
        }
        posts.forEach { showBlogIntroduction(it) }
    }

    fun showSearchResult(posts: List<BlogPostItem>) {
        if (posts.isEmpty()) {
            println("검색 결과가 없습니다.")
            return
        }
        posts.forEach { showBlogItems(it) }
    }

    private fun showBlogItems(blog: BlogPostItem) {
        buildString {
            appendLine("====================================")
            appendLine("제목: ${blog.title}")
            appendLine("링크: ${blog.link}")
            appendLine("작성일: ${formatLocalDateTime(blog.pubDate, DATE_FORMAT_PATTERN)}")
            appendLine("내용: ${blog.content.take(PREVIEW_CONTENT_LENGTH)}...")
            appendLine("====================================")
        }.let(::println)
    }

    private fun showBlogIntroduction(blog: Blog) {
        buildString {
            appendLine("====================================")
            appendLine("블로그 이름: ${blog.blogName}")
            appendLine("홈 링크: ${blog.homeLink}")
            appendLine("소개: ${blog.description}")
            appendLine("====================================")
        }.let(::println)
    }

    fun showPost(post: BlogPosts) {
        buildString {
            appendLine("    <최신 글 목록>")
            post.items.forEach { post ->
                appendLine("    제목: ${post.title}")
                appendLine("    링크: ${post.link}")
                appendLine("    작성일: ${formatLocalDateTime(post.pubDate, DATE_FORMAT_PATTERN)}")
                appendLine("    내용: ${post.content.take(PREVIEW_CONTENT_LENGTH)}...")
                appendLine("    ====================================")
            }
        }.let(::println)
    }
}
