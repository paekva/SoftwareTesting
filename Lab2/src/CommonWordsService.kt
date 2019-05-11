import database.DataBaseConnection
import database.Word

class CommonWordsService {

    fun getCommonWords(word: Word): List<String>{
        val dbc = DataBaseConnection()
        dbc.connect()
        return dbc.findSameRootWords(word)
    }
}