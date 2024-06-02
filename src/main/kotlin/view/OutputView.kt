package view

import model.Article

object OutputView {
    private val SEPARATOR: String = "-".repeat(200)

    fun showArticles(articles: List<Article>) {
        articles.forEach {
            println("Title: ${it.title}")
            println("Link: ${it.link}")
            println("PubDate: ${it.publishedDate}")
            println("Description: ${it.description}")
            println(SEPARATOR)
        }
    }
}
