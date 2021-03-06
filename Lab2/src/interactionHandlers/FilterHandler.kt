package interactionHandlers

import services.CommonWordsService
import handlerMock
import services.UserInteractionService
import commandHandler
import printErrorMsg
import printMainMsg
import services.MessagingService

class FilterHandler {
    private val uis: UserInteractionService = UserInteractionService()
    private val cws: CommonWordsService = CommonWordsService()
    private val state = State.instance
    private val msg = MessagingService.instance

    fun begin(): Unit {
        val uis = UserInteractionService()
        val availableCommandNumbers = 0..1
        val availableCommands = arrayOf<commandHandler>( ::filterByPartOfSpeech, ::handlerMock, ::handlerMock, ::handlerMock)

        printMainMsg("В данном разделе вы можете отфильтровать последние результаты поиска по различным категориям")

        if(state.getLastResults().isEmpty())
            printErrorMsg("Вами еще не было сделано ни одного поискового запроса. Сортировка не может быть выполнена")
        else {
            var answerCode = -1
            while(answerCode != 0)
                answerCode = uis.getUserCommand(availableCommandNumbers, availableCommands, msg.getFilterMenuMsg())
        }
    }

    private fun filterByPartOfSpeech(){
        printMainMsg("Фильтрация по части речи")
        println()

        val partOfSpeech = uis.getUserInput("* часть речи: ", false)
        val wordList = cws.groupBy(state.getLastResults(), partOfSpeech)

        if(wordList.isEmpty())
            printErrorMsg("Данного типа слов нет в словаре. Попробуйте вернутся в главное меню и добавить новые слова в словарь!")
        else
            uis.displayWords(wordList)
    }
}