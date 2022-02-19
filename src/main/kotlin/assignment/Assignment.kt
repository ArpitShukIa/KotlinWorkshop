package assignment

import java.util.Scanner
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
    var returnList = mutableListOf<Response>()
    val sc = Scanner(File("responses.txt"))
    sc.nextLine()

    while (sc.hasNextLine()) {
        sc.next()
        returnList.add(Response(sc.next(), sc.nextInt(), sc.next(), sc.next() == "yes"));
    }
    sc.close()
    returnList = returnList.filterNot { it.covidPositive }.toMutableList()
    returnList.sortWith(compareBy<Response> {it.branch}.thenBy {it.name})
    return returnList.toList()
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
