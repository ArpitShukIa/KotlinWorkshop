package assignment

// INSTRUCTIONS:
// 1. Read a list of student responses from text file
// 2. Filter students who are not covid positive
// 3. Sort them according to their branches, within each branch sort by names
// 4. Break branches into groups of 3
// 5. Save the student batches in a text file
import java.io.File

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
  val read: List<String> = File("src/main/kotlin/assignment/responses.txt").readLines()
  val responses = MutableListOf<Response>
  for (i in 1 until read.size()){
    val student=read.split(" ").filter{it!=""}
    val covid=if (student[4]=="yes")true else false
    val eligible = Response(read[1], read[2].toInt(), read[3],covid)
    responses.add(eligible)
  }
    return response
}

fun processResponses(responses: List<Response>): List<List<Response>> {
    return responses.filter { !it.covidPositive }.sortedWith(compareBy({it.branch},{it.name})).groupBy { it.branch}.values.chunked(3).map { it.flatten() }
}
}

fun writeToFile(batches: List<List<Response>>) {
   val write = File("src/main/kotlin/assignment/batches.txt")
  batches.ForEachIndex{ batch_no, batch -> write.appendText("Batch ${batch_no + 1}:\n")
    batch.ForEachIndex{ student_no, student -> write.appendText("${student_no + 1}. \t ${student.name} \t ${student.rollNumber} \t ${student.branch}\n") }
    write.appendText("\n")
}
}