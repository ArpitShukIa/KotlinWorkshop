package assignment

import java.io.BufferedWriter
import java.io.File
import java.io.FileWriter

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

fun String.toBool(): Boolean {
    return this.uppercase()=="YES";
}

fun main() {
    val responses = readResponses()
    val processedData = processResponses(responses)
    writeToFile(processedData)
}

fun readResponses(): List<Response> {
    val fileName = "src/main/kotlin/assignment/responses.txt"
    val lines: List<String> = File(fileName).readLines()

    var responses = mutableListOf<Response>()
    for (i in 1 until lines.size) {
        var line = lines[i]
        line = line.replace("\\s+".toRegex(), " ")
        var eachResponse = line.split(" ")
        responses.add(Response(eachResponse[2], eachResponse[3].toInt(), eachResponse[4], eachResponse[5].toBool()))
    }
    return responses.toList()
}

fun processResponses(responses: List<Response>): List<List<Response>> {
    val covidNegative = responses.filter { !it.covidPositive }
    val sortedCovidNegative = covidNegative.sortedWith(compareBy({it.branch},{it.name}))

    val groupByBranch = sortedCovidNegative.groupBy { it.branch }

    var allResponseBranchWise = mutableListOf<List<Response>>()
    groupByBranch.forEach { (k, v) ->
        allResponseBranchWise.add(v)
    }
    var threedList = allResponseBranchWise.chunked(3)
    var req = mutableListOf<List<Response>>()
    threedList.forEach { req.add(it.flatten()) }
    return req
}

fun writeToFile(batches: List<List<Response>>) {
    //batches.forEach { println(it) }

    val append = false
    val filename = "src/main/kotlin/assignment/batches.txt"
    val writer = BufferedWriter(FileWriter(filename, append))

    batches.forEach {
        val batchNumber = batches.indexOf(it)+1
        writer.write("Batch ${batchNumber}:")
        writer.newLine()
        it.forEach { singleStudent ->
            val serialNumber = it.indexOf(singleStudent)+1
            writer.write("${serialNumber}.\t${singleStudent.name}\t\t${singleStudent.rollNumber}\t\t${singleStudent.branch}")
            writer.newLine()
        }
        writer.newLine()
    }
    writer.close()
}