package controller

import model.ArticleReader
import model.RssParser
import view.InputView
import view.OutputView

class RssController {
    fun run() {
        val url = InputView.readUrl()
        val articles = RssParser.getArticles(url)
        val articleReader = ArticleReader(articles)
        val sortedArticle = articleReader.getArticles()
        OutputView.showArticles(sortedArticle)
        while (true) {
            if (InputView.readEnding()) break
            val keyword = InputView.readSearchingKeyword()
            val keywordArticles = articleReader.getArticleWithKeyword(keyword)
            OutputView.showArticles(keywordArticles)
        }
    }
}
