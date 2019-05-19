package services

import commandHandler
import database.Word
import printErrorMsg
import printSecondaryMsg
import printMainMsg
import java.util.*

class UserInteractionService {

    fun getUserCommand(availableCommandNumbers: IntRange, commandList: Array<commandHandler>, menuMsg: String): Int{
        printMainMsg("Возможные действия (введите нужную цифру для продожения):")
        printSecondaryMsg(menuMsg)

        val answerCode = getCommandNumber(availableCommandNumbers)
        println()

        if(answerCode != 0)
            commandList[answerCode-1].invoke()

        return answerCode
    }

    fun getCommandNumber(availableCommandNumbers: IntRange): Int{
        val reader = Scanner(System.`in`)
        var answerCode = -1

        while(answerCode < 0){
            try{
                answerCode = reader.nextLine().toInt()

                if(!commandInputField(answerCode, availableCommandNumbers))
                    throw Exception()
            }
            catch(e: Exception){
                printErrorMsg("Неверный ввод. Попробуйте снова!")
                answerCode = -1
            }
        }

        return answerCode
    }

    fun getUserInput(msg: String, isOptional: Boolean): String{
        var answer = ""
        val reader = Scanner(System.`in`)
        printSecondaryMsg(msg)

        while(answer == ""){
            try{
                answer = reader.nextLine()

                if(isOptional && answer.isEmpty())
                    return ""

                if(wayOut(answer))
                    return answer

                if(!textInputField(answer))
                    throw Exception()

                if(answer.isNotEmpty())
                    break
            }
            catch(e: Exception){
                printErrorMsg("Неверный ввод. Попробуйте снова!")
                answer = ""
            }
        }
        return answer
    }

    fun textInputField(value: String): Boolean{
        if(value.length > 256) return false
        return value.toLowerCase().matches("[а-яА-Я|\\s|:|\\-]+".toRegex())
    }

    private fun wayOut(value: String): Boolean{
        return value.toLowerCase().matches("q".toRegex())
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

    fun displayWordInfo(word: Word){
        println("СЛОВО ${word.getWord()}\n" +
                "КОРЕНЬ ${word.getRoot()}\n" +
                "ЗНАЧЕНИЕ ${word.getMeaning()}\n" +
                "ЧАСТЬ РЕЧИ ${word.getPartOfSpeech()}\n"+
                "ДАТА ДОБАВЛЕНИЯ ${word.getDate()}\n"+
                "СЛОВО, ОТ КОТОРОГО ОБРАЗОВАНО: ${word.getOrigin()}\n"+
                "ЯЗЫК ПРОИСХОЖДЕНИЯ: ${word.getOriginLang()}\n"+
                "ДЛИНА СЛОВА ${word.getWord().length}\n")
    }

    fun getNaturalNumber(msg: String): Int {
        val reader = Scanner(System.`in`)
        var wordsNumber = 0
        printSecondaryMsg(msg)

        while(wordsNumber<1){
            try{
                wordsNumber = reader.nextLine().toInt()
                if(wordsNumber<1)
                    throw Exception()
            }
            catch(e: Exception){
                printErrorMsg("Неверный ввод, попробуйте еще раз")
                wordsNumber = 0
            }
        }
        return wordsNumber
    }
}