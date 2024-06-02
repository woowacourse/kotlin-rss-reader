package model

import kotlin.math.min

class ArticleReader(private var articles: List<Article>) {
    init {
        articles = articles.sortedByDescending { it.publishedDate }
    }

    fun getArticles(): List<Article> {
        return articles.subList(0, min(articles.size, MAX_SIZE))
    }

    fun getArticleWithKeyword(keyword: String): List<Article> {
        return articles.filter { it.title.contains(keyword) }
    }

    companion object {
        private const val MAX_SIZE = 10
    }
}
