package services

import database.DataBaseConnection
import database.Word

class WordSettingsService {

    private val dbc: DataBaseConnection = DataBaseConnection()

    init { dbc.connect() }

    fun setPartOfSpeech(word: String, partOfSpeech: String){
        dbc.changePartOfSpeech(word, partOfSpeech)
    }

    fun getWordInfo(word: String): Word {
        return dbc.getWord(word)
    }

    fun changeWordOrigin(word: Word, origin: String, originLanguage: String) : Boolean{
        if( !dbc.checkForWordInDictionary(word) )
            return false

        dbc.changeOrigin(word.getWord(), origin, originLanguage)

        return true
    }
}