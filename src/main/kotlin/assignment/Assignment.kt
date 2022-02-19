package assignment

import java.io.File

// INSTRUCTIONS:
// 1. Read a list of student responses from text file
// 2. Filter students who are not covid positive
// 3. Sort them according to their branches, within each branch sort by names
// 4. Break branches into groups of 3
// 5. Save the student batches in a text file

data class Response(
    val name: String,
    val rollNumber: Int,
    val branch: String,
    val covidPositive: Boolean
)

fun main() {
    val responses = readResponses()
    val processedData = processResponses(responses)
    writeToFile(processedData)
}

fun readResponses(): List<Response> {

    val file = "src/main/kotlin/assignment/responses.txt"
    val data: List<String> = File(file).readLines()

    val responses:MutableList<Response> = mutableListOf()

    for (response in data.drop(1)){
        val dataItems = response.split(" ").filter { it!="" }
        val student = Response(dataItems[1], dataItems[2].toInt(), dataItems[3], dataItems[4]=="yes")
        responses.add(student)
    }

    return responses
}
fun processResponses(responses: List<Response>): List<List<Response>> {
     return responses.filter {!it.covidPositive}.sortedWith(compareBy({ it.branch }, { it.name })).groupBy { it.branch }.values.chunked(3).map { it.flatten() }
}

fun writeToFile(batches: List<List<Response>>) {
    val file = File("src/main/kotlin/assignment/batches.txt")
    file.appendText("S.No. \t\t Name \t\t Roll No. \t\t Branch \n\n")
    batches.forEachIndexed { index, batch -> file.appendText("Batch ${index + 1}:\n")
        batch.forEachIndexed { stud_index, student -> file.appendText("${stud_index + 1}. \t\t ${student.name} \t\t ${student.rollNumber} \t\t ${student.branch}\n") }
        file.appendText("\n")
    }


}
