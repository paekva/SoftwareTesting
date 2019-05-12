package services

import database.DataBaseConnection
import database.Word

class CommonWordsService {

    private val dbc: DataBaseConnection = DataBaseConnection()

    init { dbc.connect() }

    fun getAllCommonRootWords(word: Word): List<Word>{
        return dbc.findSameRootWords(word)
    }

    fun setPartOfSpeech(word: String, partOfSpeech: String){
        dbc.changePartOfSpeech(word, partOfSpeech)
    }

    fun getWordInfo(word: String): Word {
        return dbc.getWord(word)
    }
}