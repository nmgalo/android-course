class Point(
    private val x: Int = 0,
    private val y: Int = 0
) {

    override fun toString() = "Point(x=$x, y=$y)"

    override fun equals(other: Any?): Boolean {
        return other is Point && other.x == x && other.y == y
    }

    override fun hashCode(): Int {
        var result = x
        result = 31 * result + y
        return result
    }

    fun symmetryPoint() = Point(x * -1, y * -1)
}


fun main() {
    val point = Point(1, 10)
    val secondPoint = Point(2, 11)
    println(point.toString())
    println(point == secondPoint)
    println(point.symmetryPoint())
}
