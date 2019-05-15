package services

import database.Word
import printErrorMsg
import printInfoMsg
import printSuccessMsg
import java.util.*


typealias commandHandler = (el: Int) -> Unit

class UserInteractionService {

    fun getUserCommand(availableCommandNumbers: IntRange, availableCommands: Array<commandHandler>, commandList: String){
        var answerCode = 1
        val reader = Scanner(System.`in`)

        while(answerCode != 0){
            printSuccessMsg("\nВозможные действия (введите нужную цифру для продожения): ")
            printInfoMsg(commandList)

            try{
                answerCode = reader.nextInt()

                if(answerCode == 0) break
                if(!commandInputField(answerCode, availableCommandNumbers))
                    throw Exception()

                availableCommands[answerCode-1].invoke(answerCode)
            }
            catch(e: Exception){
                printErrorMsg("Неверный ввод. Попробуйте снова!")
                answerCode = 1
            }
        }
    }

    fun textInputField(value: String): Boolean{
        return value.toLowerCase().matches("[a-z]+".toRegex())
    }

    fun commandInputField(value: Int, interval: IntRange): Boolean{
        return value in interval
    }

    fun displayCommonRootWords(words: List<Word>) : List<String>{
        return words.map { word -> word.getWord() }
    }

    fun displayWordInfo(word: Word): String{
        return "СЛОВО ${word.getWord()}\n" +
                "КОРЕНЬ ${word.getRoot()}\n" +
                "ДАТА ДОБАВЛЕНИЯ ${word.getDate()}\n" +
                "ЧАСТЬ РЕЧИ ${word.getPartOfSpeech()}\n"
    }
}