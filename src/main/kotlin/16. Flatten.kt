fun main() {
    val list = listOf(
        listOf(1, 2, 3),
        listOf(4, 5, 6),
        listOf(7, 8)
    )
    println(list.flatten())
//    Alternate:
//    val newList = mutableListOf<Int>()
//    for(innerList in list) {
//        for(i in innerList) {
//            newList.add(i)
//        }
//    }
//    println(newList)
}