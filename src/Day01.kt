fun main() {

    val digitsNumbers = mapOf(
        "1" to "1",
        "2" to "2",
        "3" to "3",
        "4" to "4",
        "5" to "5",
        "6" to "6",
        "7" to "7",
        "8" to "8",
        "9" to "9"
    )
    val textNumbers = mapOf(
        "one" to "1",
        "two" to "2",
        "three" to "3",
        "four" to "4",
        "five" to "5",
        "six" to "6",
        "seven" to "7",
        "eight" to "8",
        "nine" to "9"
    )

    fun firstNumber(input: String, t: List<String>): String {
        val found = input.findAnyOf(t) ?: (Int.MAX_VALUE to "")
        return found.second
    }

    fun calibrationNumber(input: String, numbers: Map<String, String>): Int {
        var number: String = ""
        val first = numbers[firstNumber(input, numbers.keys.toList())]
        val last = numbers[firstNumber(input.reversed(), numbers.keys.map { it.reversed() }).reversed()]
        number += first + last
        return ("" + number.first() + number.last()).toInt().also { res -> println("$input -> $res") }
    }


    fun solvePart1(input: List<String>): Int {
        return input.sumOf { calibrationNumber(it, digitsNumbers) }
    }

    fun solvePart2(input: List<String>): Int {
        return input.sumOf { calibrationNumber(it, digitsNumbers + textNumbers) }
    }


    // Lee los datos
    val input = readInput("Day01")

    // Primera parte
    var result = solvePart1(input)
    check(result == 54953)
    result.println()


    // Segunda parte
    result = solvePart2(input)
    check(result == 53868)
    result.println()
}
