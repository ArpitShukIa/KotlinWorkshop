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
fun tobool(s:String):Boolean{
    return s.uppercase()=="YES"
}
fun readResponses(): List<Response> {
    val read: List<String> = File("responses.txt").readLines()
    val responses = mutableListOf<Response>()
    for (i in 1 until read.size){
        var line=read[i]
        line =line.replace("\\s+".toRegex()," ")//to delete multiple spaces
        var eachColumn =line.split(" ")
        responses.add(Response(eachColumn[2], eachColumn[3].toInt(), eachColumn[4], tobool(eachColumn[5])))
    }
      return responses.toList()
}

fun processResponses(responses: List<Response>): List<List<Response>> {
    return responses.filter { !it.covidPositive }.sortedWith(compareBy({it.branch},{it.name})).groupBy { it.branch}.values.chunked(3).map { it.flatten() }
}

fun writeToFile(batches: List<List<Response>>) {
    val fileName = File("batches.txt")
    

    batches.forEachIndexed { batchId, batch -> fileName.appendText("Batch ${batchId + 1}:\n")
        batch.forEachIndexed { s_no, student -> fileName.appendText("${s_no+1}\t\t${student.name}\t\t${student.rollNumber}\t\t${student.branch}\n")}
    
        fileName.appendText("\n")
    }
}