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
    val inputStream: InputStream = File("responses.txt").inputStream()
    val lineList = mutableListOf<String>()
    inputStream.bufferedReader().forEachLine { lineList.add(it) }

    val responses = mutableListOf<Response>()


    for(i in 1..(lineList.size-1)){
        var list = lineList[i].split("\\s".toRegex())
        list = list.filter{s -> s!= ""}
        val isCovid = if(list[4]=="yes")true else false
        val res = Response(list[1], list[2].toInt(), list[3], isCovid)
        responses.add(res)
    }

    return responses.toList()

}

fun processResponses(responses: List<Response>): List<List<Response>> {
    var notCovid = responses.filter{res -> !res.covidPositive}
    var cpy = notCovid.sortedWith(compareBy<Response> {it.branch}.thenBy{it.name})
    
    val result = cpy.groupBy{it.branch}.values.toList().withIndex().groupBy { it.index/3 }.map{ it.value.flatMap{it.value}}
   
    return result
    
}

fun writeToFile(batches: List<List<Response>>) {

    File("batches.txt").printWriter().use { out ->
        for ((batchIndex, batch) in batches.withIndex()){
            out.println("Batch ${batchIndex+1}:")
            for((studentIndex, student) in batch.withIndex()){
                out.println("${studentIndex+1}.\t${student.name}\t\t${student.rollNumber}\t\t${student.branch}")
            }
            out.println()
        }
    }

}
