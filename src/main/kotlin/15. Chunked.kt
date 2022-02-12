fun main() {
    val list = listOf(1, 2, 3, 4, 5, 6, 7, 8)
    println(list.chunked(3))
//    Alternate:
//    val newList = mutableListOf<List<Int>>()
//    var tempList = mutableListOf<Int>()
//    for (i in list) {
//        tempList.add(i)
//        if (tempList.size == 3) {
//            newList.add(tempList)
//            tempList = mutableListOf()
//        }
//    }
//    if (tempList.isNotEmpty()) {
//        newList.add(tempList)
//    }
//    println(newList)
}