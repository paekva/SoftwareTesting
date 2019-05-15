package interactionHandlers

import commandHandler
import printErrorMsg
import services.CommonWordsService
import services.UserInteractionService
import services.WordSettingsService

class SearchHandler {
    private val mainMsg = "0. возврат в главное меню " +
            "\n1. найти однокоренные по введенному слову " +
            "\n2. найти слова с омонимичными корнями по введенному слову" +
            "\n3. найти полную информацию по введенному слову" +
            "\n4. найти слова по введенному тегу"

    private val uis: UserInteractionService = UserInteractionService()
    private val cws: CommonWordsService = CommonWordsService()
    private val wss: WordSettingsService = WordSettingsService()

    fun begin(): Unit {
        val availableCommandNumbers = 1..4
        val availableCommands = arrayOf<commandHandler>( ::getCommonRootWords, ::getOmonimRootWords, ::getFullWordInfo, ::getWordsByTags)

        uis.getUserCommand(availableCommandNumbers, availableCommands, mainMsg)
    }

    private fun getCommonRootWords(){
        val input = uis.getUserInput("Введите слово для поиска однокоренных")
        val word = wss.getWordInfo(input)

        if(word == null){
            printErrorMsg("Данного слова нет в словаре. Попробуйте вернутся в главное меню и добавить это слово в словарь!")
            return
        }

        val wordList = cws.getAllCommonRootWords(word)

        uis.displayWords(wordList)
    }

    private fun getOmonimRootWords(){
        val input = uis.getUserInput("Введите слово для поиска слов с омонимичными корнями")
        val word = wss.getWordInfo(input)

        if(word == null){
            printErrorMsg("Данного слова нет в словаре. Попробуйте вернутся в главное меню и добавить это слово в словарь!")
            return
        }

        val wordList = cws.getAllOmonimRootWords(word)

        uis.displayWords(wordList)
    }

    private fun getFullWordInfo(){
        val input = uis.getUserInput("Введите слово для поиска слов с омонимичными корнями")
        val word = wss.getWordInfo(input)

        if(word == null){
            printErrorMsg("Данного слова нет в словаре. Попробуйте вернутся в главное меню и добавить это слово в словарь!")
            return
        }

        uis.displayWordInfo(word)
    }

    private fun getWordsByTags(){
        val input = uis.getUserInput("Введите тег для поиска слов")
    }
}