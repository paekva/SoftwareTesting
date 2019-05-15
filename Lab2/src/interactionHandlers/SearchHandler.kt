package interactionHandlers

import handlerMock
import services.UserInteractionService
import services.commandHandler

class SearchHandler {
    private val mainMsg = "0. возврат в главное меню " +
            "\n1. найти однокоренные по введенному слову " +
            "\n2. найти слова с омонимичными корнями по введенному слову" +
            "\n3. найти полную информацию по введенному слову" +
            "\n4. найти слова по введенному тегу"


    fun begin(el: Int): Unit {
        val uis = UserInteractionService()
        val availableCommandNumbers = 1..4
        val availableCommands = arrayOf<commandHandler>( ::handlerMock, ::handlerMock, ::handlerMock, ::handlerMock, ::handlerMock, ::handlerMock, ::handlerMock)

        uis.getUserCommand(availableCommandNumbers, availableCommands, mainMsg)
    }
}