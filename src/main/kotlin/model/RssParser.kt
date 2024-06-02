package model

import org.w3c.dom.Element
import org.w3c.dom.NodeList
import utils.removeHtmlTags
import utils.toLocalDateTime
import javax.xml.parsers.DocumentBuilderFactory

object RssParser {
    private val xmlParser by lazy { DocumentBuilderFactory.newInstance().newDocumentBuilder() }

    fun getArticles(url: String): List<Article> {
        val articles = mutableListOf<Article>()
        val xml = xmlParser.parse(url)
        val channel = xml.getElementsByTagName("channel").item(0)
        val items = (channel as Element).getElementsByTagName("item")
        for (i in 0 until items.length) {
            articles.add(elementToArticle(items, i))
        }
        return articles
    }

    private fun elementToArticle(
        items: NodeList,
        index: Int,
    ): Article {
        val item = items.item(index) as Element
        val title = item.getElementsByTagName("title").item(0).textContent
        val link = item.getElementsByTagName("link").item(0).textContent
        val pubDate = item.getElementsByTagName("pubDate").item(0).textContent
        val description = item.getElementsByTagName("description").item(0).textContent
        return Article(title, link, pubDate.toLocalDateTime(), description.removeHtmlTags())
    }
}
