package services

import database.DataBaseConnection
import database.Word

class AddWordsService {

    private val dbc: DataBaseConnection = DataBaseConnection()

    init { dbc.connect() }

    fun addWord(word: Word) : Boolean{
        return true
    }
}