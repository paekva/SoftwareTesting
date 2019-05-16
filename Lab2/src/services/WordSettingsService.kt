package services

import database.DatabaseConnection
import database.DatabaseService
import database.Word

class WordSettingsService {

    private val dbs: DatabaseService = DatabaseService()

    fun setPartOfSpeech(word: String, partOfSpeech: String): Boolean{
        return dbs.changePartOfSpeech(word, partOfSpeech)
    }

    fun getWordInfo(word: String): Word? {
        return dbs.getParticularWord(word)
    }

    fun changeWordOrigin(word: String, origin: String) : Boolean{
        return dbs.changeOrigin(word, origin)
    }

    fun changeWordOriginLanguage(word: String, originLanguage: String) : Boolean{
        return dbs.changeOriginLanguage(word, originLanguage)
    }

    fun isWordInDictionary(word: String): Boolean{
        return dbs.checkForWordInDictionary(Word(word, "", ""))
    }
}