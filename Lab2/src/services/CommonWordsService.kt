package services

import database.DatabaseConnection
import database.Word
import java.sql.Date

class CommonWordsService {

    private val dbc: DatabaseConnection = DatabaseConnection()

    init { dbc.connect() }

    fun getAllCommonRootWords(word: Word): List<Word>{
        return dbc.findSameRootWords(word)
    }

    fun groupBy(words: List<Word>, partOfSpeech: String) : List<Word>{
        return words.filter { it.getPartOfSpeech() == partOfSpeech }
    }

    fun groupBy(words: List<Word>, from: Int, to: Int) : List<Word>{
        return words.filter { it.getWord().length in from..to }
    }

    fun groupBy(words: List<Word>, from: Date, to: Date) : List<Word>{
        return words.filter { it.getDate() in from..to }
    }
}