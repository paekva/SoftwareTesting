package database

import java.util.*
import java.sql.Date as SQLDate

data class Word(private val word: String, private val root: String, private val meaning: String){
    private var date: SQLDate? = null
    private var partOfSpeech: String = ""
    private val origin: String = ""
    private val originLang: String = ""

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