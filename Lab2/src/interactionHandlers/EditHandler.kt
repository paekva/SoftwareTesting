package interactionHandlers

import handlerMock
import services.UserInteractionService
import commandHandler
import printErrorMsg
import printInfoMsg
import printSuccessMsg
import services.WordSettingsService
import wordSettingChanger

class EditHandler {
    private val mainMsg = "0. возврат в главное меню " +
            "\n1. задать слову часть речи" +
            "\n2. задать слову то слово, от которого произошло (этимология)" +
            "\n3. задать слову его язык происхождения" +
            "\n4. задать слову теги\n"

    private val uis: UserInteractionService = UserInteractionService()
    private val wss: WordSettingsService = WordSettingsService()

    fun begin(): Unit {
        val uis = UserInteractionService()
        val availableCommandNumbers = 1..4
        val availableCommands = arrayOf<commandHandler>( ::setWordPartOfSpeech, ::setWordOrigin, ::setWordOriginLanguage, ::handlerMock)

        uis.getUserCommand(availableCommandNumbers, availableCommands, mainMsg)
    }

    private fun setWordPartOfSpeech(){
        val function = wss::setPartOfSpeech
        changingWordSettings("часть речи", function)
    }

    private fun setWordOrigin(){
        val function = wss::changeWordOrigin
        changingWordSettings("слово, от которого образовано исходное, ", function)
    }

    private fun setWordOriginLanguage(){
        val function = wss::changeWordOriginLanguage
        changingWordSettings("язык происхождения ", function)
    }

    private fun changingWordSettings(setting: String, function: wordSettingChanger){
        printSuccessMsg("Введите слово и новое значение для $setting:")
        printInfoMsg("Вы можете пропустить необязательные к заполнению поля (обязательные поля помечены звездочкой *)")
        println()

        val wordInput = uis.getUserInput("* слово: ", false)
        val word = wss.isWordInDictionary(wordInput)

        if(!word) {
            printErrorMsg("Данного слова нет в словаре. Добавьте его сами или выберите другое слово")
            return
        }

        val partOfSpeech = uis.getUserInput("* $setting: ", false)
        val success = function.invoke(wordInput, partOfSpeech)

        if(success) printSuccessMsg("успешно получилось изменить $setting у слова")
        else printErrorMsg("произошла ошибка: изменить $setting не удалось")
    }
}