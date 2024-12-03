import kotlin.math.abs

fun main() {

    fun isSafe(line: List<Int>) : Boolean {
        var isIncremental = true
        var isDecremental = true
        var isInRange = true
        for(i in line.indices) {
            if (i == line.size - 1) {
                continue
            }
            if (line[i] >= line[i + 1]) {
                isIncremental = false
            }
            if (line[i] <= line[i + 1]) {
                isDecremental = false
            }
            if (abs(line[i] - line[i+1]) > 3) {
                isInRange = false
                break
            }
        }

        val isSafe = (isIncremental || isDecremental) && isInRange
//        println("line $line is safe: $isSafe")
        return isSafe
    }

    fun isSafeCheckSubLists(line: List<Int>): Boolean {
        var safe = isSafe(line)
        if (safe) {
            return true
        }

        for (i in line.indices) {
            val sublist = if (i == 0) mutableListOf() else line.subList(0, i).toMutableList()
            sublist.addAll(line.subList(i+1, line.size))
            safe = isSafe(sublist)
            if (safe) {
                return true
            }
        }
        return false
    }

    fun part1(input: List<String>): Int {
        return input
            .asSequence()
            .map { it.split(" ") }
            .map { it.map { n -> n.toInt() }.toList() }
            .count { isSafe(it) }
    }

    fun part2(input: List<String>): Int {
        return input
            .asSequence()
            .map { it.split(" ") }
            .map { it.map { n -> n.toInt() }.toList() }
            .count { isSafeCheckSubLists(it) }
    }


    val testInput = readInput("Day02_test")
    check(part1(testInput).also { println("test1: $it") } == 2)
    check(part2(testInput).also { println("test2: $it") } == 4)

    // Read the input from the `src/Day01.txt` file.
    val input = readInput("Day02")
    part1(input).println()
    part2(input).println()
}
