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
            }.filterIndexed { index, _ ->
                index == 0 || index == transformacionesEncontradas.size - 1
            }.let { transformacionesEncontradasOrdenadas ->
                var nuevaLinea = linea
                if (transformacionesEncontradasOrdenadas.size > 0) {
                    val transformacionPrimera = transformacionesEncontradasOrdenadas[0]
                    nuevaLinea = nuevaLinea.replaceRange(
                        nuevaLinea.indexOf(transformacionPrimera.second.first),
                        nuevaLinea.indexOf(transformacionPrimera.second.first) + transformacionPrimera.second.first.length,
                        transformacionPrimera.second.second.toString()
                    )
                }
                if (transformacionesEncontradasOrdenadas.size > 1) {
                    val transformacionUltima = transformacionesEncontradasOrdenadas[1]
                    nuevaLinea = nuevaLinea.reversed()
                    if (nuevaLinea.indexOf(transformacionUltima.second.first.reversed()) != -1)
                        nuevaLinea = nuevaLinea.replaceRange(
                            nuevaLinea.indexOf(transformacionUltima.second.first.reversed()),
                            nuevaLinea.indexOf(transformacionUltima.second.first.reversed()) + transformacionUltima.second.first.length,
                            transformacionUltima.second.second.toString()
                        ).reversed()
                    else
                        nuevaLinea = nuevaLinea.reversed()
                }
                println("->:  $nuevaLinea")
                nuevaLinea
            }
        }.sumOf { lineaNumeros ->
            print("linea numeros: $lineaNumeros")
            lineaNumeros.filter { caracter ->
                caracter.isDigit()
            }.let { num ->
                val n = if (num.length > 1) "" + num[0] + num[num.length - 1] else "" + num[0] + num[0]
                println("->    n: $n")
                n
            }.toInt()
        }
    }

    // test if implementation meets criteria from the description, like:
    // val testInput = readInput("Day01_test")
    // check(part1(testInput) == 1)

    val input = readInput("Day01")
    //part1(input).println()
    part2(input).println()
}
