import java.util.Scanner
import java.io.File

package assignment

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
    val returnList = mutableListOf<Response>()
    val fileContent = File("responses.txt")
    val sc = Scanner(fileContent)
    sc.nextLine()

    var name: String
    var rollNumber: Int
    var branch: String
    var covidPositive: String

    while (sc.hasNextLine()) {
        sc.next()
        name = sc.next()
        rollNumber = sc.nextInt()
        branch = sc.next()
        covidPositive = sc.next()
        if (covidPositive == "no")
            returnList.add(Response(name, rollNumber, branch, false));
    }
    sc.close()
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
