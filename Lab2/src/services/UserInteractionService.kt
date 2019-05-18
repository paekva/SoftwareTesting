package services

import commandHandler
import database.Word
import printErrorMsg
import printInfoMsg
import printSuccessMsg
import java.util.*

class UserInteractionService {

    fun getUserCommand(availableCommandNumbers: IntRange, availableCommands: Array<commandHandler>, commandList: String): Int{
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

        return answerCode
    }

    fun getUserInput(msg: String, isOptional: Boolean): String{
        var answer = ""
        val reader = Scanner(System.`in`)
        printInfoMsg(msg)

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

    fun wayOut(value: String): Boolean{
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
}