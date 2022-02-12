fun main() {
    val age = readln().toInt()
    if (age >= 18)
        println("Adult")
    else
        println("Kid")

    val salary = readln().toInt()
    val tax = if (salary > 5_00_000) 20 else 10
    println("tax = $tax%")
}