package services

import database.DatabaseConnection
import database.DatabaseService
import database.Word

class WordSettingsService {

    private val dbc: DatabaseConnection = DatabaseConnection()
    private val dbs: DatabaseService = DatabaseService()

    init { dbc.connect() }

    fun setPartOfSpeech(word: String, partOfSpeech: String){
        dbs.changePartOfSpeech(word, partOfSpeech)
    }

    fun getWordInfo(word: String): Word {
        return dbc.getWord(word)
    }

    fun changeWordOrigin(word: Word, origin: String, originLanguage: String) : Boolean{
        if( !dbs.checkForWordInDictionary(word) )
            return false

        dbs.changeOrigin(word.getWord(), origin, originLanguage)

        return true
    }
}