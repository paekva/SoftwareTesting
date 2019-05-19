package database

import java.util.*
import java.sql.Date as SQLDate

data class Word(private val word: String, private val root: String, private val meaning: String){
    private var date: SQLDate = SQLDate(Date().time)
    private var partOfSpeech: String = ""
    private var origin: String = ""
    private var originLang: String = ""

    constructor(word: String, root: String, meaning: String, partOfSp: String) : this(word, root, meaning){
        partOfSpeech = partOfSp
    }

    constructor(word: String, root: String, meaning: String, addDate: SQLDate) : this(word, root, meaning){
        date = addDate
    }

    constructor(word: String, root: String, meaning: String, partOfSp: String, addDate: SQLDate) : this(word, root, meaning){
        partOfSpeech = partOfSp
        date = addDate
    }

    constructor(word: String, root: String, meaning: String, partOfSp: String, addDate: SQLDate, wordOrigin: String, originLanguage: String) : this(word, root, meaning){
        partOfSpeech = partOfSp
        date = addDate
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