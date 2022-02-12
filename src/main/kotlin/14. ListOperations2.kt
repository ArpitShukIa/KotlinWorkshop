data class FormResponse(
    val name: String,
    val branch: String,
    val covidPositive: Boolean
)

fun main() {
    val students = listOf(
        FormResponse("Alex", "EEE", true),
        FormResponse("Bob", "CSE", false),
        FormResponse("Charlie", "CSE", false),
        FormResponse("Cherry", "EEE", true)
    )

    // 1. Print list
    println(students)

    // 2. Map it to list of student names
    println(students.map { it.name })

    // 3. Check if none is covid positive
    println(students.none { it.covidPositive })

    // 4. Filter covid positive students
    println(students.filter { it.covidPositive })

    // 5. Print list sorted by student branch
    println(students.sortedBy { it.branch })

}
