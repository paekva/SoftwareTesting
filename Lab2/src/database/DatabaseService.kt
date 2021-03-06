package database

import java.sql.SQLException
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

    fun checkForPhraseInDictionary(phrase: String): Boolean {
        try {
            val sql = "SELECT * from phrases " + "WHERE phrase = ? "
            val args = ArrayList<String>()
            args.add(phrase)

            val rs = dbc.select(sql, args)
            return rs!!.next()
        } catch (e: Exception) {
            println("An error in checkForPhraseInDictionary: \n ${e.message}")
        }
        return false
    }

    fun addWord(word: Word) {
        val st = ("INSERT INTO words " + "(word, root, meaning, adddate, partofspeech, origin, origin_lang)"
                + " VALUES (?, ?, ?, '"+word.getDate().toString()+"', ?, ?, ?)")

        val args = ArrayList<String>()
        args.add(word.getWord())
        args.add(word.getRoot())
        args.add(word.getMeaning())
        args.add(word.getPartOfSpeech())
        args.add(word.getOrigin())
        args.add(word.getOriginLang())

        dbc.insert(st, args)
    }

    fun findSameRootWords(word: Word): List<Word> {
        val strings = ArrayList<Word>()
        try {
            val sql = ("SELECT * from words "
                    + "WHERE root = ? and meaning = ? "
                    + "and not word = ?")

            val args = ArrayList<String>()
            args.add(word.getRoot())
            args.add(word.getMeaning())
            args.add(word.getWord())

            val rs = dbc.select(sql, args)

            while (rs!!.next()) {
                strings.add(dbc.getWord(rs))
            }

        } catch (ex: SQLException) {
            println("Error in findSameRootWords: \n ${ex.message}")
        }

        return strings
    }

    fun findOmonimRootWords(word: Word): List<Word> {
        val strings = ArrayList<Word>()
        try {
            val sql = ("SELECT * from words "
                    + "WHERE root = ?"
                    + "and not word = ?")

            val args = ArrayList<String>()
            args.add(word.getRoot())
            args.add(word.getWord())

            val rs = dbc.select(sql, args)

            while (rs!!.next()) {
                strings.add(dbc.getWord(rs))
            }

        } catch (ex: SQLException) {
            println("Error in findOmonimRootWords: \n ${ex.message}")
        }

        return strings
    }

    fun findWordsByPartOfSpeech(partOfSpeech: String): List<Word> {
        val strings = ArrayList<Word>()
        try {
            val sql = ("SELECT * from words "
                    + "WHERE partofspeech = ?")

            val args = ArrayList<String>()
            args.add(partOfSpeech)

            val rs = dbc.select(sql, args)

            while (rs!!.next()) {
                strings.add(dbc.getWord(rs))
            }

        } catch (ex: SQLException) {
            println("Error in findWordsByPartOfSpeech: \n ${ex.message}")
        }

        return strings
    }

    fun findPhrasesByWord(word: String): List<String>{
        val strings = arrayListOf<String>()
        try {
            val sql = ("SELECT phrase from examples "
                    + "WHERE word = ?")

            val args = ArrayList<String>()
            args.add(word)

            val rs = dbc.select(sql, args)

            while (rs!!.next()) {
                strings.add(rs.getString("phrase"))
            }

        } catch (ex: SQLException) {
            println("Error in findPhrasesByWord: \n ${ex.message}")
        }

        return strings
    }

    fun addPhrase(phrase: String) {
        val st = ("INSERT INTO phrases " + "(phrase)"
                + " VALUES (?)")

        val args = ArrayList<String>()
        args.add(phrase)

        dbc.insert(st, args)
    }

    fun addExample(word: String, phrase: String) {
        val st = ("INSERT INTO examples " + "(word, phrase)"
                + " VALUES (?, ?)")

        val args = ArrayList<String>()
        args.add(word)
        args.add(phrase)

        dbc.insert(st, args)
    }

    fun changePartOfSpeech(word: String, partOfSpeech: String): Boolean {
        val sql = ("UPDATE words "
                + "SET partofspeech = ? "
                + "WHERE word = ?")

        val args = ArrayList<String>()
        args.add(partOfSpeech)
        args.add(word)

        return dbc.insert(sql, args)
    }

    fun changeOrigin(word: String, origin: String): Boolean {
        val sql = ("UPDATE words "
                + "SET origin = ?"
                + "WHERE word = ?")

        val args = ArrayList<String>()
        args.add(origin)
        args.add(word)

        return dbc.insert(sql, args)
    }

    fun changeOriginLanguage(word: String, originLanguage: String): Boolean {
        val sql = ("UPDATE words "
                + "SET origin_lang = ?"
                + "WHERE word = ?")

        val args = ArrayList<String>()
        args.add(originLanguage)
        args.add(word)

        return dbc.insert(sql, args)
    }

    fun getParticularWord(word: String): Word? {
        var result: Word? = null
        val sql = "SELECT * from words " + "WHERE word = ? "
        val args = ArrayList<String>()
        args.add(word)
        val rs = dbc.select(sql, args)

        try {
            if (rs == null)
                return result

            while (rs.next()) {
                result = dbc.getWord(rs)
            }
        } catch (e: Exception) {
            // println(e.message)
        }

        return result
    }
}