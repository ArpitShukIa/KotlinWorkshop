fun main() {
    repeat(5) {
        println("Hello")
    }
    for (i in 1..5) {
        println(i)
    }
    for (i in 1..5 step 2) {
        println(i)
    }
    for (i in 1 until 5) {
        println(i)
    }
    for (i in "abcd") {
        println(i)
    }
}