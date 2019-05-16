import interactionHandlers.AddUI
import interactionHandlers.EditUI
import interactionHandlers.FilterUI
import interactionHandlers.SearchUI
import services.UserInteractionService

fun handlerMock (): Unit{

}

typealias commandHandler = () -> Unit
typealias wordSettingChanger = (word: String, settingValue: String) -> Boolean
const val mainMsg = "0. завершение работы программы" +
        "\n1. поиск слов по введенному слову " +
        "\n2. добавление слов и предложений" +
        "\n3. изменение существующих слов" +
        "\n4. работа с последним результатом поиска" +
        "\n5. повторить одно из ранее выбранных действий\n"

fun main(){
    val search = SearchUI()::begin
    val add = AddUI()::begin
    val edit = EditUI()::begin
    val filter = FilterUI()::begin
    val uis = UserInteractionService()
    val availableCommandNumbers = 1..5
    val availableCommands = arrayOf<commandHandler>( search, add, edit, filter, ::handlerMock)

    printSuccessMsg("Вы находитесь в программе по поиску и редактированию однокоренных слов")
    uis.getUserCommand(availableCommandNumbers, availableCommands, mainMsg)

}