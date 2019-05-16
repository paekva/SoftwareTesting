package interactionHandlers

import commandHandler
import printErrorMsg
import services.CommonWordsService
import services.UserInteractionService
import services.WordSettingsService

class SearchUI {
    private val mainMsg = "0. возврат в главное меню " +
            "\n1. найти однокоренные по введенному слову " +
            "\n2. найти слова с омонимичными корнями по введенному слову" +
            "\n3. найти полную информацию по введенному слову" +
            "\n4. найти слова по введенному тегу\n"

    private val uis: UserInteractionService = UserInteractionService()
    private val cws: CommonWordsService = CommonWordsService()
    private val wss: WordSettingsService = WordSettingsService()

    fun begin(): Unit {
        val availableCommandNumbers = 1..4
        val availableCommands = arrayOf<commandHandler>( ::getCommonRootWords, ::getOmonimRootWords, ::getFullWordInfo, ::getWordsByTags)

        uis.getUserCommand(availableCommandNumbers, availableCommands, mainMsg)
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

    private fun getFullWordInfo(){
        val input = uis.getUserInput("Введите слово для поиска слов с омонимичными корнями", false)

        val word = wss.getWordInfo(input)
        if(word == null){
            printErrorMsg("Данного слова нет в словаре. Попробуйте вернутся в главное меню и добавить это слово в словарь!")
            return
        }

        uis.displayWordInfo(word)
    }

    private fun getWordsByTags(){
        val input = uis.getUserInput("Введите тег для поиска слов", false)
    }
}