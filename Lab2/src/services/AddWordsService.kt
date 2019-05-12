package services

import database.DataBaseConnection
import database.Word

class AddWordsService {

    private val dbc: DataBaseConnection = DataBaseConnection()

    init { dbc.connect() }

    fun addWord(word: Word) : Boolean{
        if(dbc.checkForWordInDictionary(word))
            return false

        dbc.addWord(word)
        return true
    }

    fun addGroupOfWords(words: ArrayList<Word>): Boolean {
        var result = true
        words.forEach { word -> result = result && addWord(word) }
        return result
    }
}