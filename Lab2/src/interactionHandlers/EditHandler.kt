package interactionHandlers

import handlerMock
import services.UserInteractionService
import services.commandHandler

class EditHandler {
    private val mainMsg = "0. возврат в главное меню " +
            "\n1. задать слову часть речи" +
            "\n2. задать слову то слово, от которого произошло (этимология)" +
            "\n3. задать слову его язык происхождения" +
            "\n4. задать слову теги"


    fun begin(el: Int): Unit {
        val uis = UserInteractionService()
        val availableCommandNumbers = 1..4
        val availableCommands = arrayOf<commandHandler>( ::handlerMock, ::handlerMock, ::handlerMock, ::handlerMock, ::handlerMock, ::handlerMock, ::handlerMock)

        uis.getUserCommand(availableCommandNumbers, availableCommands, mainMsg)
    }
}