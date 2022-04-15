package cv07b

import java.util.*

/**
 * [InputHandler] class to read input from user and return a generic [Shape]
 */
class InputHandler {
    /** [reader] to read input from user */
    private val reader = Scanner(System.`in`)

    /**
     * Method reads data from user and returns a [Shape]
     */
    fun readData(): Shape {
        println()

        print("Input - number of lines: ")
        val lineCount: Int = reader.nextInt()

        val uniqueLines: Int = when(lineCount) {
            0 -> 0
            else -> {
                print("Input - number of unique lines: ")
                reader.nextInt()
            }
        }

        print("Input - number of corners: ")
        val corners: Int = reader.nextInt()

        print("Input - number of semicircles: ")
        val semiCircles = reader.nextInt()

        return Shape(lineCount, uniqueLines, corners, semiCircles)
    }
}