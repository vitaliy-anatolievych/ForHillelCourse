fun main() {
    println("We have a robot, please enter \n" +
            "command movement sequence for him.\n" +
            "\n" +
            "He knows the following commands: R (right), L (left), U (up), and D (down)\n" +
            "We need to find out if he will return to his original place.")
    println("\n-------------------------------------\n")
    print("Enter movement sequence for our robot: ")
    val movementSequence = readLine()
    val isRobotOriginalPlace = judgeCircle(movementSequence!!)
    println(isRobotOriginalPlace)
}

fun judgeCircle(moves: String): Boolean {
    val robot = Robot(0, 0)

    for (m in moves.indices) {
        val step = moves[m]

        when (step) {
            'R' -> robot.x++
            'L' -> robot.x--
            'U' -> robot.y++
            'D' -> robot.y--
        }
    }
    return robot.x == 0 && robot.y == 0
}

data class Robot(var x: Int, var y: Int)