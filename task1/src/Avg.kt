fun main() {
    println(avgOfEven(listOf(4, 2, 3, 4, 4, 2, 12)))
}

fun avgOfEven(items: List<Int>): Int {
    var sum = 0
    var count = 0
    for (index in 0..items.size step 2) {
        sum += items[index]
        ++count
    }
    return sum / count
}

