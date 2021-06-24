class Iterator(val lists: List<List<Int>>) {
    var isListNotEnded = true
    var countOfElements: Int = 0
    var iteration = 0

    var iterationElement = 0
    var iterationList = 0

    var mayBeSortedList: List<Int> = mutableListOf()

    init {

        // Я хочу узнать сколько у меня в целом содержится элементов во всех списках
        for (currentList: Int in lists.indices) {
            countOfElements += lists[currentList].size
            for (num in lists.get(currentList).indices) {
                val currentNum = lists.get(currentList).get(num)
                // я хочу увидеть тут метод mayBeSortedList.add(currentNum) бляха муха
            }
        }
    }

    fun hasNext(): Boolean {
        // ...
        if (iteration == countOfElements) isListNotEnded = false
        iteration++
        return isListNotEnded
    }

    //@switchingList - определяет необходимость перейти на следующий список
    fun next(): Int {
        // ...
        switchingList()
        return sortManager()
    }

    // @sortManager отдаёт отсортированное число
    private fun sortManager(): Int {
//        lists.get(iterationList).get(iterationElement++)
        println(mayBeSortedList.toString())
        return 0
    }

    //@resetIteratorElement() если мы достигли конца списка - обнуляем итератор елементов
    private fun switchingList() {
        if (iterationElement == lists.get(iterationList).size) {
            iterationList++
            resetIteratorElement()
        }
    }

    private fun resetIteratorElement() {
        iterationElement = 0
    }


}