data class Person(val firstName: String, val lastName: String = "")

fun main() {
    val person1 = Person(firstName = "Jordan", lastName = "Peterson")
    val person2 = Person("Mary")
    println(person1)
    println(person2)
    println(person1.firstName)
}