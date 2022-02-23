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

    return responses.groupBy {it.branch}.values.toList().withIndex().groupBy { it.index / 3 }.map { it.value.flatMap { it.value } }

}

fun writeToFile(batches: List<List<Response>>) {

    File("batches.txt").printWriter().use { out ->

        for ((index, batch) in batches.withIndex()) {
            out.println("Batch ${index+1}:")
            for ((studentIndex, student) in batch.withIndex())
                out.println("${studentIndex+1}.\t${student.name}\t\t${student.rollNumber}\t\t${student.branch}")
            out.println()
        }
    }
}