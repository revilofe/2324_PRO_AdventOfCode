fun main() {
    fun part1(input: List<String>): Int {
        return input.sumOf { linea ->
            linea.filter { caracter ->
                caracter.isDigit()
            }.let { num ->
                if (num.length > 1) "" + num[0] + num[num.length - 1] else "" + num[0] + num[0]
            }.toInt()
        }
    }


    fun part2(input: List<String>): Int {
        val transformaciones = listOf("one" to '1' , "two" to '2', "three" to '3', "four" to '4', "five" to '5', "six" to '6', "seven" to '7', "eight" to '8', "nine" to '9')

        return input.map { linea ->
            var nuevaLinea = linea
            print("linea: $linea")

            val transformacionesEncontradas: MutableList<Pair<Int,Pair<String, Char>>> = ArrayList()

            transformaciones.forEach { transformacion ->
                var indice = linea.indexOf(transformacion.first, 0, true)
                while (indice != -1)
                {
                    transformacionesEncontradas.add(indice to transformacion)
                    indice = linea.indexOf(transformacion.first, indice + transformacion.first.length, true)
                }
            }
            transformacionesEncontradas.filter { transformacionFiltro ->
                transformacionFiltro.first != -1
            }.sortedBy { transformacionOrden ->
                transformacionOrden.first
            }.forEach { transformacionEncontrada ->
                val transformacion = transformacionEncontrada.second
                if (nuevaLinea.indexOf(transformacion.first) != -1)
                    nuevaLinea = nuevaLinea.replaceRange(
                        nuevaLinea.indexOf(transformacion.first),
                        nuevaLinea.indexOf(transformacion.first) + transformacion.first.length,
                        transformacion.second.toString()
                    )
            }
            println("->:         $nuevaLinea")
            nuevaLinea
        }.sumOf { lineaNumeros ->
            print("linea numeros: $lineaNumeros")
            lineaNumeros.filter { caracter ->
                caracter.isDigit()
            }.let { num ->
                var n = if (num.length > 1) "" + num[0] + num[num.length - 1] else "" + num[0] + num[0]
                println("->    n: $n")
                n
            }.toInt()
        }
    }

    // test if implementation meets criteria from the description, like:
    // val testInput = readInput("Day01_test")
    // check(part1(testInput) == 1)

    val input = readInput("Day01")
    part1(input).println()
    part2(input).println()
}
