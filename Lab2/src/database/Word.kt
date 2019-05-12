package database

import java.util.*
import java.sql.Date as SQLDate

class Word(private val word: String, private val root: String){
    private var date: SQLDate? = null
    private val partOfSpeech: String? = null
    private var searched: Int? = null
    private val etymlogyWord: String? = null
    private val etymlogyLang: String? = null

    init{
        date = SQLDate(Date().time)
        searched = 0
    }

    fun getWord(): String{
        return "'$word'"
    }

    fun getRoot(): String{
        return "'$root'"
    }

    fun getSearched(): Int{
        return searched!!
    }

    fun getDate(): String{
        return "'$date'"
    }

    fun setDate(addDate: SQLDate){
        date = addDate
    }

}