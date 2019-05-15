package interactionHandlers

import handlerMock
import services.UserInteractionService
import services.commandHandler

class AddHandler {
    private val mainMsg = "0. возврат в главное меню " +
            "\n1. добавить слово" +
            "\n2. добавить группу однокоренных слов" +
            "\n3. добавить группу слов, однокоренных к заданному" +
            "\n4. добавить предложение - пример к заданному слову"


    fun begin(el: Int): Unit {
        val uis = UserInteractionService()
        val availableCommandNumbers = 1..4
        val availableCommands = arrayOf<commandHandler>( ::handlerMock, ::handlerMock, ::handlerMock, ::handlerMock, ::handlerMock, ::handlerMock, ::handlerMock)

        uis.getUserCommand(availableCommandNumbers, availableCommands, mainMsg)
    }
}