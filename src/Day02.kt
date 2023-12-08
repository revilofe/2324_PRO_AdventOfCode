fun main() {
    /*
    only 12 red cubes, 13 green cubes, and 14 blue cubes
    */

    fun createContadores():MutableMap<String,MutableList<Int>>{
        val unMapa = mutableMapOf<String,MutableList<Int>>()
        unMapa["red"] = mutableListOf()
        unMapa["green"] = mutableListOf()
        unMapa["blue"] = mutableListOf()
        return unMapa
    }


    //Game 1: 7 blue, 6 green, 3 red; 3 red, 5 green, 1 blue; 1 red, 5 green, 8 blue; 3 red, 1 green, 5 blue
    fun readData(gamesInput: List<String>): MutableMap<Int, MutableMap<String, MutableList<Int>>> {
        val games = mutableMapOf<Int,MutableMap<String,MutableList<Int>>>()

        gamesInput.forEach { game ->
            val idGame = game.split(":")[0].split(" ")[1].trim().toInt() // Game 1: -> 1
            games[idGame] = createContadores()
            val suite =
                game.split(":")[1].split(";") //7 blue, 6 green, 3 red; 3 red, 5 green, 1 blue; 1 red, 5 green, 8 blue; 3 red, 1 green, 5 blue
            suite.forEach { item -> //7 blue, 6 green, 3 red
                val round = item.split(",")
                round.forEach { cube -> // 7 blue //No hace falta guardar todos los cubos, De cada color, nos podemos quedar con el maximo, se reduce la estructura.
                    val key = cube.trim().split(" ")[1].trim()
                    val value = cube.trim().split(" ")[0].trim().toInt()
                    games[idGame]?.get(key)?.add(value)
                }
            }
        }
        return games
    }

    fun gameCorrect(
        games: MutableMap<Int, MutableMap<String, MutableList<Int>>>,
        idGame: Int,
        cubes: Map<String, Int>
    ): Int {
        var idGameCorrect = 0
        if (((games[idGame]?.get("red")?.max() ?: Int.MAX_VALUE) <= (cubes["red"] ?: 0)) &&
            ((games[idGame]?.get("green")?.max() ?: Int.MAX_VALUE) <= (cubes["green"] ?: 0)) &&
            ((games[idGame]?.get("blue")?.max() ?: Int.MAX_VALUE) <= (cubes["blue"] ?: 0))
        )
            idGameCorrect = idGame
        return idGameCorrect
    }

    fun gamePower(
        games: MutableMap<Int, MutableMap<String, MutableList<Int>>>,
        idGame: Int
    ): Int {

        var idGamePower: Int = 0
        idGamePower = games[idGame]?.values?.fold(1) {acc, cubes ->
            cubes.max() * acc} ?: 0
        return idGamePower
    }

    fun solvePart1(input: List<String>): Int {
        val cubes = mapOf("red" to 12, "green" to 13, "blue" to 14)
        val games = readData(input)
        return games.keys.sumOf { idGame ->
            gameCorrect(games, idGame, cubes)
        }
    }

    fun solvePart2(input: List<String>): Int {
        val cubes = mapOf("red" to 12, "green" to 13, "blue" to 14)
        val games = readData(input)
        return games.keys.sumOf { idGame ->
            gamePower(games, idGame)
        }
    }

    var result = 0
    // Lee los datos
    val testInput = readInput("Day02test")
    val input = readInput("Day02")

    // Primera parte
    result = solvePart1(testInput)
    result.println()
    check(result == 8)

    result = solvePart1(input)
    result.println()
    check(result == 2369)

    // Segunda parte
    result = solvePart2(testInput)
    result.println()
    check(result == 2286)

    result = solvePart2(input)
    result.println()
    //check(result == 0)

}
