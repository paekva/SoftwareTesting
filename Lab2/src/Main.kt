import interactionHandlers.AddHandler
import interactionHandlers.EditHandler
import interactionHandlers.FilterHandler
import interactionHandlers.SearchHandler
import services.MessagingService
import services.UserInteractionService

fun handlerMock (): Unit{}
typealias commandHandler = () -> Unit
typealias wordSettingChanger = (word: String, settingValue: String) -> Boolean
typealias getSelectionLists = (root: String) -> List<String>

fun main(){
    val search = SearchHandler()::begin
    val add = AddHandler()::begin
    val edit = EditHandler()::begin
    val filter = FilterHandler()::begin

    val uis = UserInteractionService()
    val ms = MessagingService.instance

    val availableCommandNumbers = 1..5
    val availableCommands = arrayOf<commandHandler>( search, add, edit, filter)

    printSuccessMsg("Вы находитесь в программе по поиску и редактированию однокоренных слов")
    uis.getUserCommand(availableCommandNumbers, availableCommands, ms.getMainMenuMsg())
}