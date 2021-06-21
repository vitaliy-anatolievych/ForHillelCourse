fun main() {
    print("Press jewels: ")
    val jewels = readLine()

    print("Press stones: ")
    val stones = readLine()

    val countJewels = numJewelsInStones(jewels!!, stones!!)
    print(countJewels)
}

fun numJewelsInStones(jewels: String, stones: String): Int {
    var countJewels = 0

    for (j in jewels.indices) {
        for (s in stones.indices) {
            if (jewels[j] == stones[s]) {
                countJewels++
            }
        }
    }
    return countJewels
}