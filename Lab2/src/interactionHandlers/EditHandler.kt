package interactionHandlers

import handlerMock
import services.UserInteractionService
import commandHandler
import getSelectionLists
import printErrorMsg
import printInfoMsg
import printSuccessMsg
import services.MessagingService
import services.SelectionListService
import services.WordSettingsService
import wordSettingChanger

class EditHandler {
    private val uis: UserInteractionService = UserInteractionService()
    private val wss: WordSettingsService = WordSettingsService()
    private val sls: SelectionListService = SelectionListService()
    private val msg = MessagingService.instance

    fun begin(): Unit {
        val availableCommandNumbers = 0..4
        val availableCommands = arrayOf<commandHandler>( ::setWordPartOfSpeech, ::setWordOrigin, ::setWordOriginLanguage, ::handlerMock)

        var answerCode = -1
        while(answerCode != 0)
            answerCode = uis.getUserCommand(availableCommandNumbers, availableCommands, msg.getEditMenuMsg())
    }

    private fun setWordPartOfSpeech(){
        val function = wss::setPartOfSpeech
        val selection = sls::getAvailablePartsOfSpeech
        changingWordSettings("часть речи", function, selection)
    }

    private fun setWordOrigin(){
        val function = wss::changeWordOrigin
        changingWordSettings("слово, от которого образовано исходное, ", function, ::handleMock)
    }

    private fun setWordOriginLanguage(){
        val function = wss::changeWordOriginLanguage
        val selection = sls::getAvailableOriginLanguage
        changingWordSettings("язык происхождения ", function, selection)
    }

    private fun changingWordSettings(setting: String, function: wordSettingChanger, selectionListGetter: getSelectionLists){
        printSuccessMsg("Установка нового значения для $setting:")
        println()

        val wordInput = uis.getUserInput("* слово: ", false)
        val word = wss.isWordInDictionary(wordInput)

        if(!word) {
            printErrorMsg("Данного слова нет в словаре. Добавьте его сами или выберите другое слово")
            return
        }

        val newSetting =  sls.menuWithDatabaseOptions("", selectionListGetter)
        val success = function.invoke(wordInput, newSetting)

        if(success) printSuccessMsg("успешно получилось изменить $setting у слова")
        else printErrorMsg("произошла ошибка: изменить $setting не удалось")
    }

    private fun handleMock(root: String):List<String>{
        return arrayListOf()
    }
}