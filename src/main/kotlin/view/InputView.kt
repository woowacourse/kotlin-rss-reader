package view

object InputView {
    fun readUrl(): String {
        print("Enter URL: ")
        return readln()
    }

    fun readSearchingKeyword(): String {
        print("Enter keyword: ")
        return readln()
    }

    fun readEnding(): Boolean {
        print("종료를 원하시면 Y, 키워드 검색을 원하시면 N을 입력해주세요: ")
        val input = readln()
        return input == "Y"
    }
}
