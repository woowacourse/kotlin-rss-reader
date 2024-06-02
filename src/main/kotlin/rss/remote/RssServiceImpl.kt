package rss.remote

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlinx.coroutines.withContext
import org.w3c.dom.Element
import org.w3c.dom.NodeList
import rss.BlogPost
import rss.BlogPostItem
import rss.parseSystemDateTime
import java.net.HttpURLConnection
import java.net.URL
import javax.xml.parsers.DocumentBuilder
import javax.xml.parsers.DocumentBuilderFactory

object RssServiceImpl : RssService {
    private val mutex = Mutex()

    override suspend fun fetchBlogPosts(url: List<String>): List<BlogPost> = withContext(Dispatchers.IO) {
        coroutineScope {
            val posts = mutableListOf<BlogPost>()
            url.forEach { url ->
                launch {
                    val items = fetchRSSFrom(url)
                    val blogPosts = parseRSS(items)
                    mutex.withLock {
                        posts.add(blogPosts)
                    }
                }
            }
            posts
        }
    }

    override suspend fun fetchBlogPost(url: String): BlogPost = withContext(Dispatchers.IO) {
        val items = fetchRSSFrom(url)
        parseRSS(items)
    }

    private fun fetchRSSFrom(url: String): NodeList {
        val connection = URL(url).openConnection() as HttpURLConnection
        connection.requestMethod = "GET"
        val inputStream = connection.inputStream
        if (connection.responseCode != HttpURLConnection.HTTP_OK) error("블로그 로드 실패 ${connection.responseCode}")
        val dom = documentBuilder().parse(inputStream)
        inputStream.close()
        return dom.getElementsByTagName("item")
    }

    private suspend fun parseRSS(items: NodeList): BlogPost = coroutineScope {
        val posts = mutableListOf<BlogPostItem>()
        val parentElement = items.item(0).parentNode as Element
        val blogName = parentElement.toTextContent("title")
        val blogLink = parentElement.toTextContent("link")
        val blogDescription = parentElement.toTextContent("description")
        repeat(items.length) { index ->
            val element = items.item(index) as Element
            val title = element.toTextContent("title")
            val link = element.toTextContent("link")
            val pubDate = parseSystemDateTime(element.toTextContent("pubDate"))
            val description = element.toTextContent("description")
            posts.add(BlogPostItem(title, link, pubDate, description))
        }
        BlogPost(blogName, blogLink, blogDescription, posts)
    }

    private fun documentBuilder(): DocumentBuilder {
        return DocumentBuilderFactory
            .newInstance()
            .newDocumentBuilder()
    }

    private fun Element.toTextContent(tagName: String): String {
        return getElementsByTagName(tagName).item(0).textContent
    }
}
