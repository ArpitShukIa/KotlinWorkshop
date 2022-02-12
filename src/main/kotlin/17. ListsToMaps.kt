data class Student(
    val name: String,
    val branch: String
)

fun main() {
    val students = listOf(
        Student("Arpit", "CSE"),
        Student("Rohit", "ECE"),
        Student("Aditya", "MNC"),
        Student("Priya", "CSE"),
        Student("Muskan", "ECE")
    )
    println(students.groupBy { it.branch })
    println(students.associate { it.name to it.branch })
    println(students.associateBy { it.name })
}