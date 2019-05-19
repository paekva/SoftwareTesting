package services

import database.DatabaseService
import database.Word
import printErrorMsg
import java.sql.Date

class CommonWordsService {

    private val dbs: DatabaseService = DatabaseService()
    private val wss: WordSettingsService = WordSettingsService()

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

    fun getAllPhrasesByWord(wordInput: String) : List<String>? {
        if(!wss.isWordInDictionary(wordInput))
            return null

        return dbs.findPhrasesByWord(wordInput)
    }

    fun getAllWordsByRoot(wordInput: String) : List<Word>? {
        val word = Word("", wordInput, "")
        return dbs.findOmonimRootWords(word)
    }

    fun getAllWordsByPartOfSpeech(partOfSpeech: String) : List<Word>? {
        return dbs.findWordsByPartOfSpeech(partOfSpeech)
    }

    fun groupBy(words: List<Word>, partOfSpeech: String) : List<Word>{
        if(words.isNullOrEmpty())
            return arrayListOf()

        return words.filter { it.getPartOfSpeech() == partOfSpeech }
    }

    fun groupBy(words: List<Word>, from: Int, to: Int) : List<Word>{
        if(words.isNullOrEmpty())
            return arrayListOf()

        return words.filter { it.getWord().length in from..to }
    }
}