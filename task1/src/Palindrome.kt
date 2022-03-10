fun main() {
    println(isPalindrome("aia"))
    println(isPalindrome("jaia"))
    println(isPalindrome("haia"))
    println(isPalindrome("აი რა მზის სიზმარია"))
}

fun isPalindrome(input: String): Boolean {
    val word = input.replace(" ", "")
    word.forEachIndexed { index, item ->
        if (word[word.length - index - 1] != item) return false
    }
    return true
}


