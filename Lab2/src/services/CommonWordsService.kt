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
        if(!wss.isWordInDictionary(wordInput)){
            printErrorMsg("Данного слова нет в словаре")
            return null
        }

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
        return words.filter { it.getPartOfSpeech() == partOfSpeech }
    }

    fun groupBy(words: List<Word>, from: Int, to: Int) : List<Word>{
        return words.filter { it.getWord().length in from..to }
    }

    fun groupBy(words: List<Word>, from: Date, to: Date) : List<Word>{
        return words.filter { it.getDate().compareTo(from) >= 0  && it.getDate().compareTo(to) <= 0 }
    }
}