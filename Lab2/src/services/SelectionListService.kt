package services

import database.DatabaseConnection
import getSelectionLists
import printSecondaryMsg
import java.util.*

class SelectionListService {

    private val dbc: DatabaseConnection = DatabaseConnection()
    private val uis: UserInteractionService = UserInteractionService()

    init { dbc.connect() }

    fun menuWithDatabaseOptions(root: String, selectionListGetter: getSelectionLists, msg: String): String{
        val selectionList = selectionListGetter.invoke(root)
        var inputCommand = 0

        if(selectionList.isNotEmpty() && selectionList[0] != ""){
            printSecondaryMsg("Вы можете выбрать $msg из предложенных или ввести новое:\n")

            var menuMsg = "0. Выбрать новое значение\n"
            selectionList.forEachIndexed { index, s -> menuMsg +="${index+1}. $s\n" }
            printSecondaryMsg(menuMsg)

            inputCommand = uis.getCommandNumber(0..selectionList.size)
        }

        if(inputCommand == 0)
            return uis.getUserInput("* введите новое значение: ", false)

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