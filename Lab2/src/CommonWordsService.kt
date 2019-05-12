import database.DataBaseConnection
import database.Word

class CommonWordsService {

    private val dbc: DataBaseConnection = DataBaseConnection()

    init{
        dbc.connect()
    }

    fun getCommonWords(word: Word): List<Word>{
        return dbc.findSameRootWords(word)
    }

    fun setPartOfSpeech(word: String, partOfSpeech: String){
        dbc.changePartOfSpeech(word, partOfSpeech)
    }

    fun getWordInfo(word: String): Word {
        return dbc.getWord(word)
    }

    fun addWord(word: Word): Boolean{
        val w = dbc.getWord(word.getWord())
        println(w)
        if(w != null){
            println("hello")
            return false
        }

        println("2")
        dbc.addWord(word)
        return true
    }
}