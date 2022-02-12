fun main() {
    val list = listOf(1, 2, 3, 4, 5)
    val doubles = listOf(1.1, 2.34, 5.68)
    val mixed = listOf(1, "Arpit", 2.0, listOf(1, 2, 3))
    println(list[0])
    println(doubles)
    println(mixed)
    for (i in mixed) {
        println(i)
    }

    val newList = mutableListOf(1, 2, 3)
    newList.add(4)
    newList.remove(2)
    newList += 10
    newList -= 1
    println(newList)

    val list1 = listOf(1, 2, 3)
    val list2 = listOf(3, 4, 5)
    println(list1 + list2)
    println(list1 - list2)

    // Similarly to List, Kotlin has Set as well
}