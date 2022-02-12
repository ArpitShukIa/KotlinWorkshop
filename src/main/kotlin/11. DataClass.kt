data class Person(
    val name: String,
    val age: Int,
    val married: Boolean = true
)

fun main() {
    val person1 = Person(name = "John", age = 24, married = false)
    val person2 = Person("Mary", 30)
    println(person1)
    println(person2)
    println(person1.name)
}