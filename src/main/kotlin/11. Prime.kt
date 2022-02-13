fun main() {
    println(isPrime(2))
    println(isPrime(4))
    println(isPrime(17))
    println(isPrime(27))
}

fun isPrime(n: Int) = (2 until n).none { n % it == 0 }

//fun checkPrime(n: Int) {
//    var isPrime = true
//    for (i in 2 until n) {
//        if (n % i == 0) {
//            isPrime = false
//            break
//        }
//    }
//    if (isPrime)
//        println("Prime")
//    else
//        println("Not prime")
//}