class Iterator(val lists: List<List<Int>>) {
    private var isEndedElement: Boolean = false



    fun hasNext(): Boolean {
        // ...
        return !isEndedElement
    }

    fun next(): Int {
        // ...
        var currentNumber = lists[0][0]

        lists.get(0).forEach {
            if (it == currentNumber) {
                return currentNumber
            }
        }
        
        isEndedElement = true
        return currentNumber
    }

}