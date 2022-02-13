//     *
//    ***
//   *****
//  *******
fun main() {
    val n = 5
    for (i in 1..n) {
        println(" " * (n - i) + "*" * (2 * i - 1))
    }
}

operator fun String.times(n: Int) = repeat(n)