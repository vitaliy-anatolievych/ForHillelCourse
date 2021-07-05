fun main() {
    val lists = listOf(
        listOf(1, 3, 5),
        listOf(2, 2, 3, 6),
        listOf(1, 3, 7)
    )

    val iterator = Iterator(lists)
    while (iterator.hasNext()) {
        print("${iterator.next()} ")
    }
    // 1, 1, 2, 2, 3, 3, 3, 5, 6, 7
}

