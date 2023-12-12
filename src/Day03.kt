fun main() {
    fun lineasAAnalizar(lineasAdyacentes: MutableList<String>, input: List<String>, index: Int) {
        val dimension = input[index].length
        lineasAdyacentes.clear()
        if (index == 0) {
            lineasAdyacentes.add(".".repeat(dimension))
        } else {
            lineasAdyacentes.add(input[index - 1])
        }
        lineasAdyacentes.add(input[index])
        if (index == dimension - 1) {
            lineasAdyacentes.add(".".repeat(dimension))
        } else {
            lineasAdyacentes.add(input[index + 1])
        }
    }

    fun esSimbolo(c: Char): Boolean {
        var es = (!c.isDigit() && c != '.')
        if (es)
            println(c)
        return es
    }

    fun tieneSimboloAdyacente(lineasAdyacentes: MutableList<String>, index: Int): Boolean {
        val dimension = lineasAdyacentes[0].length

        // Recorre los caracteres adyacentes para ver si es un simbolo
        (0..2).forEach { fila ->
            (-1..1).forEach { desplazamientoCaracter ->
                if ((index + desplazamientoCaracter >= 0) && (index + desplazamientoCaracter < dimension)) {
                    if (esSimbolo(lineasAdyacentes[fila][index + desplazamientoCaracter])) {
                        return true
                    }
                }
            }
        }
        return false
    }

    fun sumaPartNumbersDeLinea(lineasDeAnalisis: MutableList<String>): Int {
        val lineaActual = 1
        var numero = ""
        var tieneSimboloAdyacente = false
        var sumaPartNumbers = 0
        lineasDeAnalisis[lineaActual].forEachIndexed { indice, caracter ->
            if (caracter.isDigit()) {
                if (!tieneSimboloAdyacente) {
                    tieneSimboloAdyacente = tieneSimboloAdyacente(lineasDeAnalisis, indice)
                }
                numero += caracter

            }
            if ((!caracter.isDigit() || indice == lineasDeAnalisis[lineaActual].length-1) && (numero != "")) { // Si el caracter no es digito o es final de linea, y estaba leyendo un numero
                if (tieneSimboloAdyacente) { // Si cualquiera de sus digitos, tenia un simbolo adyacente
                    sumaPartNumbers += numero.toInt()
                    tieneSimboloAdyacente = false
                    println("ADYACENTE: " + numero)
                } else {
                    println("No simbolo adyacente: " + numero)
                }
                numero = ""
            }
        }
        return sumaPartNumbers
    }

    /**
     *  You turn around to see a slightly-greasy Elf with a wrench and a look of surprise. "Sorry, I wasn't expecting anyone! The gondola lift isn't working right now; it'll still be a while before I can fix it." You offer to help.
     *  The engineer explains that an engine part seems to be missing from the engine, but nobody can figure out which one. If you can add up all the part numbers in the engine schematic, it should be easy to work out which part is missing.
     *  The engine schematic (your puzzle input) consists of a visual representation of the engine. There are lots of numbers and symbols you don't really understand, but apparently any number adjacent to a symbol, even diagonally, is a "part number" and should be included in your sum. (Periods (.) do not count as a symbol.)
     */

    fun solvePart1(input: List<String>): Int {
        var partNumber = 0
        val lineasEnEstudio: MutableList<String> = mutableListOf()
        input.forEachIndexed { index: Int, s: String ->
            lineasAAnalizar(lineasEnEstudio, input, index)
            partNumber += sumaPartNumbersDeLinea(lineasEnEstudio)
        }

        return partNumber
    }

    /*
        fun solvePart2(input: List<String>): Int {
            val cubes = mapOf("red" to 12, "green" to 13, "blue" to 14)
            val games = readData(input)
            return games.keys.sumOf { idGame ->
                gamePower(games, idGame)
            }
        }
    */
    var result: Int

// Lee los datos
    val testInput = readInput("Day03test")
    val input = readInput("Day03")

// Primera parte
    result = solvePart1(testInput)
    result.println()
    check(result == 4361)

    result = solvePart1(input)
    result.println()

// Segunda parte
    /*
        result = solvePart2(testInput)
        result.println()
        check(result == 2286)

        result = solvePart2(input)
        result.println()
    */
}
