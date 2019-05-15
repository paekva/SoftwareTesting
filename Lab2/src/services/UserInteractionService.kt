package services

import commandHandler
import database.Word
import printErrorMsg
import printInfoMsg
import printSuccessMsg
import java.util.*

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

                availableCommands[answerCode-1].invoke()
            }
            catch(e: Exception){
                printErrorMsg("Неверный ввод. Попробуйте снова!")
                answerCode = 1
            }
        }
    }

    fun getUserInput(msg: String): String{
        var answer = ""
        val reader = Scanner(System.`in`)
        printInfoMsg(msg)

        while(answer == ""){
            try{
                answer = reader.next()

                if(!textInputField(answer))
                    throw Exception()
            }
            catch(e: Exception){
                printErrorMsg("Неверный ввод. Попробуйте снова!")
                answer = ""
            }
        }
        return answer
    }

    fun textInputField(value: String): Boolean{
        return value.toLowerCase().matches("[а-я]+".toRegex())
    }

    fun commandInputField(value: Int, interval: IntRange): Boolean{
        return value in interval
    }

    fun displayCommonRootWords(words: List<Word>) : List<String>{
        return words.map { word -> word.getWord() }
    }

    fun displayWords(words: List<Word>){
        return words.forEach{ w -> println(w.getWord())}
    }

    fun displayWordInfo(word: Word): String{
        return "СЛОВО ${word.getWord()}\n" +
                "КОРЕНЬ ${word.getRoot()}\n" +
                "ЗНАЧЕНИЯ ${word.getMeaning()}\n" +
                "ДАТА ДОБАВЛЕНИЯ ${word.getDate()}\n" +
                "ЧАСТЬ РЕЧИ ${word.getPartOfSpeech()}\n"
    }
}