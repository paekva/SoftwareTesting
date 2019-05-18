package services

import database.DatabaseConnection
import getSelectionLists
import printErrorMsg
import printInfoMsg
import java.util.*

class SelectionListService {

    private val dbc: DatabaseConnection = DatabaseConnection()
    private val uis: UserInteractionService = UserInteractionService()

    init { dbc.connect() }

    fun menuWithDatabaseOptions(root: String, selectionListGetter: getSelectionLists): String{
        val selectionList = selectionListGetter.invoke(root)
        var inputCommand = 0

        printInfoMsg("Вы можете выбрать значение из предложенных или ввести новое:")
        println()

        if(selectionList.isNotEmpty() && selectionList[0] != ""){
            printInfoMsg("0. Выбрать новое значение")
            println()
            selectionList.forEachIndexed { index, s -> printInfoMsg("${index+1}. $s\n")}

            val reader = Scanner(System.`in`)
            var isInputValid = false
            while(!isInputValid){
                inputCommand = reader.nextInt()
                isInputValid = uis.commandInputField(inputCommand, 0..selectionList.size)

                if(!isInputValid)
                    printErrorMsg("Неверный ввод, попробуйте еще раз")
            }
        }
        else {
            printInfoMsg("Для данного значения нет списка предложенных")
            println()
        }

        if(inputCommand == 0)
            return uis.getUserInput("* введите значение: ", false)

        return selectionList[inputCommand-1]
    }

    fun getAvailableMeanings(root: String): List<String> {
        val result = ArrayList<String>()
        val sql = "SELECT DISTINCT meaning FROM words " + "WHERE root = ? "
        val args = ArrayList<String>()
        args.add(root)
        val rs = dbc.select(sql, args)

        try {
            if (rs == null) {
                return result
            }

            while (rs.next()) {
                result.add(dbc.getWord(rs).getMeaning())
            }
        } catch (e: Exception) {
            println("Error in getAvailableMeanings: \n ${e.message}")
        }

        return result
    }

    fun getAvailablePartsOfSpeech(root: String): List<String> {
        val result = ArrayList<String>()
        val sql = "SELECT DISTINCT partofspeech FROM words"
        val args = ArrayList<String>()
        val rs = dbc.select(sql, args)

        try {
            if (rs == null)
                return result

            while (rs.next())
                result.add(dbc.getWord(rs).getPartOfSpeech())

        } catch (e: Exception) {
            println("Error in getAvailablePartsOfSpeech: \n ${e.message}")
        }

        return result
    }

    fun getAvailableOriginLanguage(root: String): List<String> {
        val result = ArrayList<String>()
        val sql = "SELECT DISTINCT origin_lang FROM words"
        val args = ArrayList<String>()
        val rs = dbc.select(sql, args)

        try {
            if (rs == null)
                return result

            while (rs.next())
                result.add(dbc.getWord(rs).getOriginLang())

        } catch (e: Exception) {
            println("Error in getAvailableOriginLanguage: \n ${e.message}")
        }

        return result
    }
}