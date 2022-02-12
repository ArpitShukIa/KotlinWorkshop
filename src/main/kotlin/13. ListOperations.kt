import kotlin.math.abs

fun main() {
    val list = listOf(-1, 2, -3, 4, 1, -2, 5)

    // 1. Print list
    println(list)

    // 2. Map it to a list of squared elements
    println(list.map { it * it })

    // 3. Filter positives
    println(list.filter { it > 0 })

    // 4. Print sorted list
    println(list.sorted())

    // 5. Print list sorted by absolute values
    println(list.sortedBy { abs(it) })

    // 6. Get a subset of list.
    println(list.take(3))

    // 7. Count negatives
    println(list.count { it < 0 })
}
