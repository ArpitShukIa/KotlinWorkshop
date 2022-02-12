fun main() {
    val a = readln().toInt()
    val b = readln().toInt()
    val result = sum(a, b)
    println(result)
    println("$a + $b = ${a + b}")
}

fun sum(x: Int, y: Int): Int {
    return x + y
}
// fun sum(x: Int, y: Int) = x + y