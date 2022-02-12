fun main() {
    println("92394926".containsOnlyDigits())

    val list = mutableListOf(1, 2, 3, 4)
    list.swap(0, 2)
    println(list)
}

fun String.containsOnlyDigits() = all { it.isDigit() }

fun MutableList<Int>.swap(index1: Int, index2: Int) {
    val temp = this[index1]
    this[index1] = this[index2]
    this[2] = temp
}