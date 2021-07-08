class Iterator(val lists: List<List<Int>>) {
    var isListNotEnded = true
    var countOfElements: Int = 0
    var iteration = 0

    var mayBeSortedList = mutableListOf<Int>()

    init {

        // Я хочу узнать сколько у меня в целом содержится элементов во всех списках
        for (currentList: Int in lists.indices) {
            countOfElements += lists[currentList].size
            for (num in lists.get(currentList).indices) {
                val currentNum = lists.get(currentList).get(num)
                mayBeSortedList.add(currentNum)
            }
        }
        mayBeSortedList.sort()
    }

    fun hasNext(): Boolean {
        // ...
        if (iteration == countOfElements) isListNotEnded = false
        return isListNotEnded
    }

    fun next(): Int {
        // ...
        return mayBeSortedList.get(iteration++)
    }
}