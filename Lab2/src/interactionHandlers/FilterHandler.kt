package interactionHandlers

import services.CommonWordsService
import handlerMock
import services.UserInteractionService
import commandHandler
import printErrorMsg
import printInfoMsg
import printSuccessMsg
import services.WordSettingsService
import wordSettingChanger

class FilterHandler {
    private val mainMsg = "0. возврат в главное меню " +
            "\n1. фильтр по части речи"+
            "\n2. фильтр по длине слова\n" /*+
            "\n2. задать слову то слово, от которого произошло (этимология)" +
            "\n3. задать слову его язык происхождения" +
            "\n4. задать слову теги\n"*/

    private val uis: UserInteractionService = UserInteractionService()
    private val cws: CommonWordsService = CommonWordsService()
    private val state = State.instance

    fun begin(): Unit {
        val uis = UserInteractionService()
        val availableCommandNumbers = 1..1
        val availableCommands = arrayOf<commandHandler>( ::filterByPartOfSpeech, ::handlerMock, ::handlerMock, ::handlerMock)

        printSuccessMsg("В данном разделе вы можете отфильтровать последние результаты поиска по различным категориям")

        if(state.getLastResults().isEmpty())
            printErrorMsg("Вами еще не было сделано ни одного поискового запроса. Сортировка не может быть выполнена")
        else
            uis.getUserCommand(availableCommandNumbers, availableCommands, mainMsg)
    }

    private fun filterByPartOfSpeech(){
        printSuccessMsg("Фильтрация по части речи")
        println()

        val partOfSpeech = uis.getUserInput("* часть речи: ", false)
        val wordList = cws.groupBy(state.getLastResults(), partOfSpeech)

        if(wordList.isEmpty())
            printErrorMsg("Данного типа слов нет в словаре. Попробуйте вернутся в главное меню и добавить новые слова в словарь!")
        else
            uis.displayWords(wordList)
    }
}