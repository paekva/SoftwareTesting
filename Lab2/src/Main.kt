import interactionHandlers.AddHandler
import interactionHandlers.EditHandler
import interactionHandlers.FilterHandler
import interactionHandlers.SearchHandler
import services.UserInteractionService

fun handlerMock (): Unit{

}

typealias commandHandler = () -> Unit
typealias wordSettingChanger = (word: String, settingValue: String) -> Boolean
typealias getSelectionLists = (root: String) -> List<String>

const val mainMsg = "0. завершение работы программы" +
        "\n1. поиск слов по введенному слову " +
        "\n2. добавление слов и предложений" +
        "\n3. изменение существующих слов" +
        "\n4. работа с последним результатом поиска" +
        "\n5. повторить одно из ранее выбранных действий\n"

fun main(){
    val search = SearchHandler()::begin
    val add = AddHandler()::begin
    val edit = EditHandler()::begin
    val filter = FilterHandler()::begin

    val uis = UserInteractionService()
    val availableCommandNumbers = 1..5
    val availableCommands = arrayOf<commandHandler>( search, add, edit, filter, ::handlerMock)

    printSuccessMsg("Вы находитесь в программе по поиску и редактированию однокоренных слов")
    uis.getUserCommand(availableCommandNumbers, availableCommands, mainMsg)
}