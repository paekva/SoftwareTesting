import services.UserInteractionService
import java.util.*

fun oneHandler (el:Int): Unit{
    println("hello from option $el")
}

typealias commandHandler = (el: Int) -> Unit

fun main(){
    val uis = UserInteractionService()
    var answerCode = 1
    val reader = Scanner(System.`in`)
    val availableCommandNumbers = 1..7
    val availableCommands = arrayOf<commandHandler>( ::oneHandler, ::oneHandler, ::oneHandler, ::oneHandler, ::oneHandler, ::oneHandler, ::oneHandler)

    printSuccessMsg("Вы находитесь в программе по поиску и редактированию однокоренных слов")

    while(answerCode != 0){
        showFullMenu()

        try{
            answerCode = reader.nextInt()

            if(answerCode == 0) break
            if(!uis.commandInputField(answerCode, availableCommandNumbers))
                throw Exception()

            availableCommands[answerCode-1].invoke(answerCode)
        }
        catch(e: Exception){
            printErrorMsg("Неверный ввод. Попробуйте снова!")
            answerCode = 1
        }
    }

}

fun showFullMenu(){
    printInfoMsg("Возможные действия (введите нужную цифру для продожения): " +
            "\n0. завершение работы программы" +
            "\n1. поиск слов по введенному слову " +
            "\n2. полная информация о слове" +
            "\n3. добавление слов" +
            "\n4. добавление предложения" +
            "\n5. изменение существующих слов" +
            "\n6. работа с последним результатом поиска" +
            "\n7. повторить одно из ранее выбранных действий")
}

fun printSuccessMsg(msg: String){
    println("\u001B[32m $msg \u001B[0m ")
}

fun printErrorMsg(msg: String){
    println("\u001B[41m $msg \u001B[0m")
}

fun printInfoMsg(msg: String?){
    println("\u001B[36m $msg  \u001B[0m")
}