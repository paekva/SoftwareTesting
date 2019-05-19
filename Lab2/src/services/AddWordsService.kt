package services

import database.DatabaseService
import database.Word

class AddWordsService {
    private val dbs: DatabaseService = DatabaseService()
    private val wss: WordSettingsService = WordSettingsService()

    fun addWord(word: Word) : Boolean{
        if(dbs.checkForWordInDictionary(word))
            return false

        dbs.addWord(word)
        return true
    }

    fun addGroupOfWords(words: ArrayList<Word>): Boolean {
        var result = true
        words.forEach { word -> result = result && addWord(word) }
        return result
    }

    fun addPhrase(words: List<String>, phrase: String): Boolean{
        dbs.addPhrase(phrase)

        words.forEach { w -> if(wss.isWordInDictionary(w)) dbs.addExample(w, phrase) }
        return true
    }
}