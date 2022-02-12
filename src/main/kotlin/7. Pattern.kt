//     *
//    ***
//   *****
//  *******
fun main() {
    val n = 5
    repeat(n) {
        println(" " * (n - it) + "*" * (2 * it + 1))
    }
}

operator fun String.times(n: Int) = repeat(n)