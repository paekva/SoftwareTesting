package interactionHandlers

import commandHandler
import database.DatabaseService
import database.Word
import handlerMock
import printErrorMsg
import printInfoMsg
import printSuccessMsg
import services.AddWordsService
import services.UserInteractionService
import services.WordSettingsService
import java.util.*
import java.sql.Date as SQLDate

class AddUI {
    private val mainMsg = "0. возврат в главное меню " +
            "\n1. добавить слово" +
            "\n2. добавить группу однокоренных слов" +
            "\n3. добавить группу слов, однокоренных к заданному" +
            "\n4. добавить предложение - пример к заданному слову\n"

    private val uis: UserInteractionService = UserInteractionService()
    private val wss: WordSettingsService = WordSettingsService()
    private val aws: AddWordsService = AddWordsService()

    fun begin(): Unit {
        val uis = UserInteractionService()
        val availableCommandNumbers = 1..4
        val availableCommands = arrayOf<commandHandler>( ::addNewWord, ::handlerMock, ::handlerMock, ::handlerMock)

        uis.getUserCommand(availableCommandNumbers, availableCommands, mainMsg)
    }

    private fun addNewWord(){
        printSuccessMsg("Введите слово и всю необходимую информацию по нему:")
        printInfoMsg("Вы можете пропустить необязательные к заполнению поля (обязательные поля помечены звездочкой *)")
        println()

        val word = uis.getUserInput("* слово: ", false)
        val isInDictionary = wss.isWordInDictionary(word)
        if(isInDictionary) {
            printErrorMsg("Данное слово уже есть в словаре, попробуйте другую опцию программы!")
            return
        }

        val root = uis.getUserInput("* корень слова: ", false)
        val meaning = meaningInput(root)
        val partOfSpeech = uis.getUserInput("часть речи: ", true)
        val origin = uis.getUserInput("слово, от которого оно произошло: ", true)
        val originLang = uis.getUserInput("язык происхождения: ",true)

        val newWord = Word(word, root, meaning, partOfSpeech, SQLDate(Date().time), origin, originLang)
        val success = aws.addWord(newWord)

        if(success) printSuccessMsg("добавление слова $word прошло успешно")
        else printErrorMsg("произошла ошибка: слово $word не добавлено, попробуйте снова")
    }

    private fun meaningInput(root: String): String{
        printInfoMsg("Вы можете выбрать значение слова из предложенных для данного корня или ввести новое:")
        println()
        val meaningsList = aws.getMeanings(root)

        printInfoMsg("0. Выбрать новое значение")
        println()
        meaningsList.forEachIndexed { index, s -> printInfoMsg("${index+1}. $s\n")}


        val reader = Scanner(System.`in`)
        var isInputValid = false
        var inputCommand = 0
        while(!isInputValid){
            inputCommand = reader.nextInt()
            isInputValid = uis.commandInputField(inputCommand, 0..meaningsList.size)

            if(!isInputValid)
                printErrorMsg("Неверный ввод, попробуйте еще раз")
        }


        if(inputCommand == 0){
            return uis.getUserInput("* введите новое значение: ", false)
        }
        return meaningsList[inputCommand-1]
    }
}