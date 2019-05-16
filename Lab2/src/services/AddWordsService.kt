package services

import database.DatabaseConnection
import database.DatabaseService
import database.Word

class AddWordsService {
    private val dbs: DatabaseService = DatabaseService()

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

    fun addPhrase(word: Word, phrase: String): Boolean{
        if(!dbs.checkForWordInDictionary(word))
            return false

        dbs.addPhrase(phrase)
        return true
    }

    fun getMeanings(root: String): List<String>{
        val meanings = dbs.getMeanings(root)
        return meanings
    }
}