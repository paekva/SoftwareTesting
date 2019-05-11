package database

import java.util.*
import java.sql.Date as SQLDate

class Word(private val word: String, private val root: String){
    private var addTime: SQLDate? = null
    private val partOfSpeech: String? = null
    private var searched: Int? = null
    private val etymlogyWord: String? = null
    private val etymlogyLang: String? = null

    init{
        addTime = SQLDate(Date().time)
        searched = 0
    }

    fun getWord(): String{
        return "'$word'"
    }

    fun getRoot(): String{
        return "'$root'"
    }

    fun getAddTime(): String{
        return "'$addTime'"
    }

    fun setAddTime(date: SQLDate){
        addTime = date
    }

    fun getSearched(): Int{
        return searched!!
    }
}