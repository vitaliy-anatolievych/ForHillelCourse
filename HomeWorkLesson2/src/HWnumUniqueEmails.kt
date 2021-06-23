import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashSet

fun main() {
    // Записываем данные от пользователя одной строкой в @emails
    println("Please enter emails: ")
    val emails = readLine()

    // @inputEmailsList хранит в себе необработанные email которые пришли от пользователя
    val inputEmailsList = emails?.split(",") ?: emptyList()

    // Создаём отдельный класс для работы с email @ManagerEmail, и передаём в конструктор список(List<String>) email
    val managerEmail = ManagerEmail(inputEmailsList)

    // Когда под капотом все процессы были завершены, выводим результат на экран.
    managerEmail.printResult()
}

class ManagerEmail(inputEmailsList: List<String>) {
    // Временное хранилище для обработанных emails
    val tmpGarbageEmail = HashSet<String>()



    init {
        // Отдаём каждый емеил в руки @parserEmail где будет происходить магия
        for (email in inputEmailsList.indices) {
            val currentEmail = inputEmailsList[email]
            tmpGarbageEmail.add(parserEmail(currentEmail))
        }
    }

    fun printResult() {
        // Возвращаем количество уникальных email(число)
        println(tmpGarbageEmail.size)
    }

    /**
     * Описание алгоритма парсера:
     * Идея: разбить email на две части имя и домен,
     * согласно условию: имя и и.м.я - считается одинаковым
     * если встречается +, то всё что после него игнорируется пример: имя+вася и имя - считается одинаковыми
     * выполнить данные условия и соединить обратно части для дальнейшей проверки на уникальность
     * Занимется этим @handlerUnnecessaryCharacters
     *
     * @atPosition - узнаём позицию @ чтобы раздробить email на имя и домен
     * @nameCurrentEmail - присваиваем имя до @
     * @domainCurrentEmail - присваиваем домен после @
     * @handlerUnnecessaryCharacters - обработчик который удаляет нежелательные символы для подальшего сравнения
     * @clearEmail - возвращаем уже обработанный согласно условиям массив нам чистый email
     */
    private fun parserEmail(email: String): String {
        val atPosition = findPositionSymbol(email, '@')
        if (atPosition == 0) return "Error: this is not email"

        val nameCurrentEmail = email.substring(0, atPosition).toLowerCase()
        val domainCurrentEmail = email.substring(atPosition + 1, email.lastIndex + 1).toLowerCase()

        // Обработчик условий возвращает нам чистый email
        val clearEmail = handlerUnnecessaryCharacters(nameCurrentEmail, domainCurrentEmail)
        return clearEmail
    }

    /**
     * Что необходимо выполнить
     * 1. Проверка на множественный знак @ в имени и домене
     * 2. Удалить в имени: точки , отбросить всё после знака +,
     * 3. имя не должно начинаться со знака +
     */
    private fun handlerUnnecessaryCharacters(nameCurrentEmail: String, domainCurrentEmail: String): String {
        // Введение промежуточных переменных где мы будем изменять имя и домен
        var resultName: String
        var resultDomain: String

        // 1. Проверка на множественный знак @, и удаление его.
        resultName = nameCurrentEmail.replace("@", "")
        resultDomain = domainCurrentEmail.replace("@", "")

        // 2. Отбросить всё после знака +, и удалить все точки
        val plusPosition = findPositionSymbol(resultName, '+') // Находим его позицию
        if (plusPosition != 0) resultName = resultName.substring(0, plusPosition) // отрезаем строку от начала до +
        resultName = resultName.replace(".", "") // убираем все точки

        // 3. Отбросить знак + в начале
        if (resultName.startsWith("+")) resultName = resultName.replace("+", "")

        // После всех манипуляций возвращаем email
        return "$resultName@$resultDomain"
    }

    /**
     * Простой метод для поиска @ и + в строке, возвращает позицию(число) символа в этой строке
     */
    private fun findPositionSymbol(email: String, symbol: Char): Int {
        var symbolPosition = 0
        for (atFinder in email.indices) {
            val at = email[atFinder]
            if (at.equals(symbol)) symbolPosition = atFinder
        }
        return symbolPosition
    }
}