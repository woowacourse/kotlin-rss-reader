package rss

object InputView {
    fun inputBlogKeyword(): String {
        print("Q. 찾고자 하는 글이 있다면 입력해주세요: ")
        return readln()
    }

    fun inputBlogName(): String {
        print("찾고자 하는 블로그의 이름을 입력해주세요: ")
        return readln()
    }
}
