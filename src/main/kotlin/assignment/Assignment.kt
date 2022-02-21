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
    val responseFile: List<String> = File("responses.txt").readLines()
    val responses = mutableListOf<Response>()

    for (rw in responseFile){
       val inputs = rw.split(" ").filter{it != ""}
       val student = Response(inputs[2],inputs[3].toInt(),inputs[4],inputs[5]=="yes")
        responses.add(student)
    }
    return responses.toList()
}

fun processResponses(responses: List<Response>): List<List<Response>> {
    val covidNeg = responses.filter{ !it.covidPositive }
    val sortByBranchAndName = covidNeg.sortedWith(compareBy({it.branch},{it.name}))
    val groupByBranch = sortByBranchAndName.groupBy { it.branch }
    return groupByBranch.chunked(3).map{ it.flatten() }
}

fun writeToFile(batches: List<List<Response>>) {


    val batchesFile = File("batches.txt")
    //batchesFile.appendText("S No.    Name      Roll Number       Branch     Covid Positive")
    batches.forEach{  listOfResponse ->

        batchesFile.appendText("Batch ${batches.indexOf(listOfResponse)+1}: \n")
        listOfResponse.forEach{
            batchesFile.appendText("${listOfResponse.indexOf(it) + 1}.\t ${it.name}\t\t${it.rollNumber}\t\t${it.branch}\n") }
        }
      //  batchesFile.appendText("\n")

}




