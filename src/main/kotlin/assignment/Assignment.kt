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

// NOTE: I LIKE EXPLICITLY STATING TYPES AT MOST PLACES EXCEPT RANGED FOR LOOPS. ALTHOUGH INTELLIJ HELPS.

fun main() {
    val responses = readResponses()
    val processedData = processResponses(responses)
    writeToFile(processedData)
}

fun readResponses(): List<Response> {
    val responseObj: MutableList<Response> = mutableListOf()

    // Read the raw input
    val inputFile: String = "src/main/kotlin/assignment/responses.txt"
    val inputLines: List<String> = File(inputFile).readLines()

    // Return a Response instance list after working with the input lines
    for (line in inputLines.drop(1)) {
        val str = line.split(" ")

        val finalStr = str.filter { it != "" }

        // Finally, add a complete Response object to the list
        responseObj.add(Response(finalStr[1], finalStr[2].toInt(), finalStr[3], finalStr[4] == "yes"))
    }

    // return as a List
    return responseObj.toList()
}

fun processResponses(responses: List<Response>): List<List<Response>> {
    // Filter covid positive ones out
    val noCovid: List<Response> = responses.filter { !it.covidPositive }

    // Sort them by branch and then their names
    val sortedVal: List<Response> = noCovid.sortedWith(compareBy({ it.branch }, { it.name }))
    val branchGroup = sortedVal.groupBy { it.branch }                // It's a Map. Too long and intellij helps

    // 3 Groups
    return  branchGroup.values.chunked(3).map { it.flatten() }
}

fun writeToFile(batches: List<List<Response>>) {
    // Get the output file ready
    val outputFile: File = File("src/main/kotlin/assignment/batches.txt")
    outputFile.appendText("\n--Text added by Abhinav--\n")

    // append the contents to the text file
    batches.forEachIndexed { batchIndex, batch ->
        outputFile.appendText("Batch ${batchIndex + 1}:\n")

        batch.forEachIndexed { studentIndex, student ->
            outputFile.appendText("${studentIndex + 1}.\t${student.name}\t\t${student.rollNumber}\t\t${student.branch}\n") }

        outputFile.appendText("\n")
    }
}
