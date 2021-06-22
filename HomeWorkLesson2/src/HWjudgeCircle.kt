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
            'R' -> robot.coordX++
            'L' -> robot.coordX--
            'U' -> robot.coordY++
            'D' -> robot.coordY--
        }
    }
    return robot.coordX == 0 && robot.coordY == 0
}

data class Robot(var coordX: Int, var coordY: Int)