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
    //writeToFile(processedData)
    //readResponses()
    println(processedData)
}


fun readResponses(): List<Response> {
    val inputStream: InputStream = File("src\\main\\kotlin\\assignment\\responses.txt").inputStream()
    val responses = inputStream.bufferedReader().use { it.readLines() }
    val studentresponses = mutableListOf<Response>()
    val len1 = responses.lastIndex
    for (i in 1..len1){
        val len = responses[i].length
        var j = 10
        var n = 0
        val branch = responses[i].slice(36..38)
        val roll = responses[i].slice(22..28)
        var covid = false
        val cond = responses[i].slice(53..53)
        if (cond == "y") {
            covid = true
            }
        while (j < len){
            if (responses[i][j].isWhitespace()) {
                n = j-1
                break
            }
            j ++
        }
        val name = responses[i].slice(10..n)
        studentresponses.add(Response(name, roll.toInt(), branch, covid))
        }
    return studentresponses
    }


fun processResponses(responses: List<Response>): List<List<Response>> {
    val students = mutableListOf<Response>()
    for (i in responses) {
        if (!i.covidPositive){
            students.add(i)
        }
    }
    val studentlist = students.sortedWith(compareBy({ it.branch }, {it.name}))
    TODO()
}


/*
fun writeToFile(batches: List<List<Response>>) {
    TODO()
}

 */