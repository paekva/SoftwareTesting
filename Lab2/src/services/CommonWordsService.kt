package services

import database.DatabaseService
import database.Word
import java.sql.Date

class CommonWordsService {

    private val dbs: DatabaseService = DatabaseService()

    fun getAllCommonRootWords(inputWord: String): List<Word>?{
        val word = dbs.getParticularWord(inputWord)

        if(word == null)
            return null
        else
            return dbs.findSameRootWords(word)
    }

    fun getAllOmonimRootWords(wordInput: String) : List<Word>? {
        val word = dbs.getParticularWord(wordInput)

        if(word == null)
            return null
        else
            return dbs.findOmonimRootWords(word)
    }

    fun groupBy(words: List<Word>, partOfSpeech: String) : List<Word>{
        return words.filter { it.getPartOfSpeech() == partOfSpeech }
    }

    fun groupBy(words: List<Word>, from: Int, to: Int) : List<Word>{
        return words.filter { it.getWord().length in from..to }
    }

    fun groupBy(words: List<Word>, from: Date, to: Date) : List<Word>{
        return words.filter { it.getDate().compareTo(from) >= 0  && it.getDate().compareTo(to) <= 0 }
    }
}