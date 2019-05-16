package database

import java.util.*
import java.sql.Date as SQLDate

data class Word(private val word: String, private val root: String, private val meaning: String){
    private var date: SQLDate? = null
    private var partOfSpeech: String = ""
    private var origin: String = ""
    private var originLang: String = ""

    init {
        date = SQLDate(Date().time)
    }

    constructor(word: String, root: String, meaning: String, partOfSp: String) : this(word, root, meaning){
        partOfSpeech = partOfSp
    }

    constructor(word: String, root: String, meaning: String, adddate: SQLDate) : this(word, root, meaning){
        date = adddate
    }

    constructor(word: String, root: String, meaning: String, partOfSp: String, adddate: SQLDate) : this(word, root, meaning){
        partOfSpeech = partOfSp
        date = adddate
    }

    constructor(word: String, root: String, meaning: String, partOfSp: String, adddate: SQLDate, wordOrigin: String, originLanguage: String) : this(word, root, meaning){
        partOfSpeech = partOfSp
        date = adddate
        origin = wordOrigin
        originLang = originLanguage
    }

    fun getWord(): String{
        return word
    }

    fun getRoot(): String{
        return root
    }

    fun getMeaning(): String{
        return meaning
    }

    fun getDate(): SQLDate{
        return date!!
    }

    fun getOrigin(): String{
        return origin
    }

    fun getOriginLang(): String{
        return originLang
    }

    fun getPartOfSpeech(): String{
        return partOfSpeech
    }
}