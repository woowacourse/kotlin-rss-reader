package rss

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import java.time.LocalDateTime

class BlogTest {
    @Test
    fun `최근에 발행된 글을 가져올 수 있다`() {
        // given
        val today = LocalDateTime.now()
        val blogPost =
            fakeBlogPost(
                today.minusDays(1),
                today,
                today.minusDays(2),
                today.plusDays(3),
            )
        // when
        val actual = blogPost.recentBlogPosts(size = 3)
        // then
        val expect =
            fakeBlogPost(
                today.plusDays(3),
                today,
                today.minusDays(1),
            ).posts
        actual shouldBe expect
    }

    @Test
    fun `찾고자 하는 단어가 apple 일 때, 제목에 apple 이 포함된 글을 가져올 수 있다`() {
        // given
        val searchKeyword = "apple"
        val blogPost =
            fakeBlogPost(
                fakeBlogPostItem(title = "apple"),
                fakeBlogPostItem(title = "banana"),
                fakeBlogPostItem(title = "apple pie"),
                fakeBlogPostItem(title = "apple juice"),
                fakeBlogPostItem(title = "orange"),
            )
        // when
        val actual = blogPost.findBlogPostsByTitle(searchKeyword)
        // then
        val expect =
            fakeBlogPost(
                fakeBlogPostItem(title = "apple"),
                fakeBlogPostItem(title = "apple pie"),
                fakeBlogPostItem(title = "apple juice"),
            ).posts
        actual shouldBe expect
    }
}
