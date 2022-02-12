fun main() {
    println(sum(1, 2, 3))
}

fun sum(vararg numbers: Int): Int {
    return numbers.sum()
}