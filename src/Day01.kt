fun main() {
    fun calibrationNumber(calibrationText: String): Int {
        val firsNumber = calibrationText.first { it.isDigit() }
        val lastNumber = calibrationText.last { it.isDigit() }
        return "${firsNumber}${lastNumber}".toInt().also { numero ->  println("$calibrationText -> $numero")  }
    }

    fun solvePart1(input: List<String>): Int {
        return input.sumOf { calibrationNumber(it) }
    }


    fun transformation(calibrationText: String, t:List<String>): String {
        var calText = calibrationText
        val tRev = t.map { it.reversed() }
        val begin = calText.findAnyOf(t) ?: (Int.MAX_VALUE to "")
        if (begin.first < calText.length)
            calText = calText.replaceFirst(begin.second, (t.indexOf(begin.second) + 1).toString())
            val end = calText.reversed().findAnyOf(tRev) ?: (Int.MAX_VALUE to "")
            if (end.first < calText.length) {
                calText = calText.reversed().replaceFirst(end.second, (tRev.indexOf(end.second) + 1).toString()).reversed()
        }
        return calText
    }

    fun solvePart2(input: List<String>): Int {
        val transformaciones = listOf(
            "one",
            "two",
            "three",
            "four",
            "five",
            "six",
            "seven",
            "eight",
            "nine"
        )
        return input.sumOf {
            calibrationNumber(
                transformation(it, transformaciones)
            )
        }

    }

// test if implementation meets criteria from the description, like:
// val testInput = readInput("Day01_test")
// check(part1(testInput) == 1)

    val input = readInput("Day01")
    val result = solvePart1(input)
    check(result == 54953)
    result.println()
    solvePart2(input).println() //53868. Correct! //53748
}
