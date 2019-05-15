package interactionHandlers

import commandHandler
import handlerMock
import services.UserInteractionService

class AddHandler {
    private val mainMsg = "0. возврат в главное меню " +
            "\n1. добавить слово" +
            "\n2. добавить группу однокоренных слов" +
            "\n3. добавить группу слов, однокоренных к заданному" +
            "\n4. добавить предложение - пример к заданному слову"


    fun begin(): Unit {
        val uis = UserInteractionService()
        val availableCommandNumbers = 1..4
        val availableCommands = arrayOf<commandHandler>( ::handlerMock, ::handlerMock, ::handlerMock, ::handlerMock, ::handlerMock, ::handlerMock, ::handlerMock)

        uis.getUserCommand(availableCommandNumbers, availableCommands, mainMsg)
    }
}