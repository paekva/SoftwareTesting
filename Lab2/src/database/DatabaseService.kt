package database

import java.util.ArrayList

class DatabaseService {
    private val dbc: DatabaseConnection = DatabaseConnection()

    init { dbc.connect() }

    fun checkForWordInDictionary(word: Word): Boolean {
        try {
            val sql = "SELECT * from words " + "WHERE word = ? "
            val args = ArrayList<String>()
            args.add(word.getWord())

            val rs = dbc.select(sql, args)
            return rs!!.next()
        } catch (e: Exception) {
            println("An error in checkForWordInDictionary: \n ${e.message}")
        }
        return false
    }

    fun addWord(word: Word) {
        val st = ("INSERT INTO words " + "(word, root, meaning, adddate, searched, partofspeech, origin, origin_lang)"
                + " VALUES (?, ?, ?, '"+word.getDate().toString()+"','"+word.getSearched().toString()+"', ?, ?, ?)")

        val args = ArrayList<String>()
        args.add(word.getWord())
        args.add(word.getRoot())
        args.add(word.getMeaning())
        args.add(word.getPartOfSpeech())
        args.add(word.getOrigin())
        args.add(word.getOriginLang())

        dbc.insert(st, args)
    }
}