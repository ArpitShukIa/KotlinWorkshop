package assignment
import java.io.File
//  INSTRUCTIONS:
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
    val fileName= "src/main/kotlin/assignment/responses.txt"
    val file =File(fileName)
    return file.readLines()
        .drop(1)
        .map{ val words=it.trim().split(Regex(" +"))
            Response(
                name= words[1],
                rollNumber = words[2].toInt(),
                branch = words[3],
                covidPositive = words[4].toBoolean()
            )
    }

    }

fun processResponses(responses: List<Response>): List<List<Response>>{
    val temp = (responses.filterNot { it.covidPositive }).sortedWith(compareBy({it.branch},{it.name}))

    val temp1=((temp.groupBy{it.branch}.values).chunked(3)).map{it.flatten()}
    print(temp1)
    return temp1

}

fun writeToFile(batches: List<List<Response>>) {
    val fileName= "src/main/kotlin/assignment/batchesNew.txt"
    val file = File(fileName)
    file.createNewFile()
    file.writeText("")
    for (index in 0 until batches.size){
        println(index)
        file.appendText("Batch ${index+1}:\n")
        for(j in batches[index].indices){
            file.appendText("${j+1}.\t\t${batches[index][j].name}\t\t${batches[index][j].rollNumber}\t\t ${batches[index][j].branch}")
            file.appendText("\n")
        }
        file.appendText("\n")
    }
}
