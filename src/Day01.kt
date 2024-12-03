import kotlin.math.abs

fun main() {

    fun readInputLine(line: String): Pair<Int, Int> = line
        .split(" ")
        .let { Pair(it.first(), it.last())}
        .let { Pair(it.first.toInt(), it.second.toInt()) }

    fun part1(input: List<String>): Int {
        val firstList = mutableListOf<Int>()
        val secondList = mutableListOf<Int>()
        input
            .map { readInputLine(it) }
            .forEach {
                firstList.add(it.first)
                secondList.add(it.second)
            }
        firstList.sort()
        secondList.sort()

        return firstList.zip(secondList)
            .sumOf { abs(it.first - it.second) }
    }

    fun part2(input: List<String>): Int {
        val destinationCounts = mutableMapOf<Int, Int>()
        val locationIds = input
            .map { readInputLine(it) }
            .onEach { destinationCounts.merge(it.second, 1, Int::plus) }
            .map { it.first }

        return locationIds
            .map { it * (destinationCounts[it] ?: 0) }
            .sum()
    }


    val testInput = readInput("Day01_test")
    check(part1(testInput) == 11)
    check(part2(testInput) == 31)

    // Read the input from the `src/Day01.txt` file.
    val input = readInput("Day01")
    part1(input).println()
    part2(input).println()
}
