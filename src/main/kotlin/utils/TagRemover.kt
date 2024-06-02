package utils

fun String.removeHtmlTags(): String {
    return this.replace(Regex("<[^>]*>"), "").trim()
}
