package interactionHandlers

import commandHandler
import printErrorMsg
import services.CommonWordsService
import services.MessagingService
import services.UserInteractionService
import services.WordSettingsService

class SearchHandler {
    private val uis: UserInteractionService = UserInteractionService()
    private val cws: CommonWordsService = CommonWordsService()
    private val wss: WordSettingsService = WordSettingsService()
    private val msg = MessagingService.instance

    fun begin(): Unit {
        val availableCommandNumbers = 0..5
        val availableCommands = arrayOf<commandHandler>( ::getCommonRootWords, ::getOmonimRootWords,
            ::getAllWordsByRoot, ::getAllWordsByPartOfSpeech, ::getFullWordInfo)

        var answerCode = -1
        while(answerCode != 0)
            answerCode = uis.getUserCommand(availableCommandNumbers, availableCommands, msg.getSearchMenuMsg())
    }

    private fun getCommonRootWords(){
        val input = uis.getUserInput("Введите слово для поиска однокоренных", false)

        val wordList = cws.getAllCommonRootWords(input)

        if(wordList == null )
            printErrorMsg("Данного типа слов нет в словаре. Попробуйте вернутся в главное меню и добавить новые слова в словарь!")
        else{
            uis.displayWords(wordList)
            val state = State.instance
            state.setLastResults(wordList)
        }
    }

    private fun getOmonimRootWords(){
        val input = uis.getUserInput("Введите слово для поиска слов с омонимичными корнями", false)

        val wordList = cws.getAllOmonimRootWords(input)
        if(wordList == null )
            printErrorMsg("Данного типа слов нет в словаре. Попробуйте вернутся в главное меню и добавить новые слова в словарь!")
        else{
            uis.displayWords(wordList)
            val state = State.instance
            state.setLastResults(wordList)
        }
    }

    private fun getAllWordsByRoot(){
        val input = uis.getUserInput("Введите корень для поиска слов: ", false)

        val wordList = cws.getAllWordsByRoot(input)
        if(wordList == null )
            printErrorMsg("Данного типа слов нет в словаре. Попробуйте вернутся в главное меню и добавить новые слова в словарь!")
        else{
            uis.displayWords(wordList)
            val state = State.instance
            state.setLastResults(wordList)
        }
    }

    private fun getAllWordsByPartOfSpeech(){
        val input = uis.getUserInput("Введите часть речи для поиска слов: ", false)

        val wordList = cws.getAllWordsByPartOfSpeech(input)
        if(wordList == null )
            printErrorMsg("Данного типа слов нет в словаре. Попробуйте вернутся в главное меню и добавить новые слова в словарь!")
        else{
            uis.displayWords(wordList)
            val state = State.instance
            state.setLastResults(wordList)
        }
    }

    private fun getFullWordInfo(){
        val input = uis.getUserInput("Введите слово для поиска слов с омонимичными корнями: ", false)

        val word = wss.getWordInfo(input)
        if(word == null){
            printErrorMsg("Данного слова нет в словаре. Попробуйте вернутся в главное меню и добавить это слово в словарь!")
            return
        }

        uis.displayWordInfo(word)
    }
}