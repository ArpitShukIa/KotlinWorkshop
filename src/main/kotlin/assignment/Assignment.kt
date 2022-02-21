package assignment
import java.io.File
import java.io.InputStream

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
    val inputStream : InputStream = File("src/main/kotlin/assignment/responses.txt").inputStream()
    val inputString : List<String> = inputStream.bufferedReader().use { it.readLines() }

    val responses = mutableListOf<Response>()
    for (i in 1 until inputString.size) {
        val eachline = inputString[i].splitToSequence(' ').filter{it.isNotEmpty()}.filter { it.isNotEmpty() }.toList()
        responses.add(Response(eachline[1], eachline[2].toInt(), eachline[3], eachline[4]!="no"))
    }
    return responses

}
fun processResponses(responses: List<Response>): List<List<Response>> {
    return responses.filter { !it.covidPositive }.sortedWith(compareBy({ it.branch }, { it.name })).groupBy { it.branch }.values.chunked(3).map { it.flatten() }
}

fun writeToFile(batches: List<List<Response>>) {
    File("src/main/kotlin/assignment/batches.txt").bufferedWriter().use { out ->
        for((index, value) in batches.withIndex()) {
            out.write("Batch ${index+1}:")
            out.newLine()
            for((studIndex, student) in value.withIndex()){
                out.write("${studIndex+1}.\t${student.name}\t\t${student.rollNumber}\t\t${student.branch}")
                out.newLine()
            }
            out.newLine()
        }
    }
}
