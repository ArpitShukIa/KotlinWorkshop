fun main() {
    val ints = listOf(1, 2, 3, 1, 1, 2)
    val strings = listOf("Apple", "Banana", "Apple", "Orange")
    println(ints.countEquals(1))
    println(strings.countEquals("Apple"))
}

fun <T> List<T>.countEquals(element: T) = count { it == element }