fun main() {
    val map = mapOf(
        1 to "one",
        2 to "two",
        3 to "three"
    )
    println(map)
    println(map[1])
    println(map[4])

    val newMap = mutableMapOf<Int, String>()
    newMap[4] = "four"
    newMap[5] = "five"
    newMap -= 4
    println(newMap)
}