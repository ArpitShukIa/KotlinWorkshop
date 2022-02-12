fun main() {
    println("Arpit" - "Mohit")
    println(12345[4])

    var s = "Arpit"
    s--
    println(s)
}

operator fun String.minus(s: String) = filter { it !in s }

operator fun String.dec() = dropLast(1)

operator fun Int.get(index: Int) = toString()[index]