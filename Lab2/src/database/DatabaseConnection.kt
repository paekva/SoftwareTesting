package database

import java.sql.*
import java.util.ArrayList

internal class DatabaseConnection {
    private val dbUrl = "jdbc:postgresql://127.0.0.1:5432/dictionary"
    private val user = "postgres"
    private val password = "rfnz98grf"
    private var connection: Connection? = null


    @Throws(SQLException::class)
    internal fun connect() {
        connection = DriverManager
            .getConnection(dbUrl, user, password)
    }

    internal fun insert(SQL: String, args: List<String>): Boolean {
        try {
            val statement = connection!!.prepareStatement(SQL)
            for (i in args.indices) {
                statement.setString(i + 1, args[i])
            }
            println(statement)
            statement.executeUpdate()
        } catch (ex: SQLException) {
            println("An error in insert: \n ${ex.message}")
            return false
        }
        return true
    }

    internal fun select(SQL: String, args: List<String>): ResultSet? {
        var statement: PreparedStatement

        try {
            statement = connection!!.prepareStatement(SQL)
            for (i in args.indices) {
                statement.setString(i + 1, args[i])
            }

            //println(statement)
        } catch (e: Exception) {
            println("Error in select:\n ${e.message}")
            return null
        }

        return statement.executeQuery()
    }

    internal fun getWord(rs: ResultSet): Word {
        var word = ""
        var root = ""
        var meaning = ""
        var partOfSpeech = ""
        var date = Date(1)
        var origin = ""
        var originLang = ""

        try {
            val rsMetaData = rs.metaData
            val numberOfColumns = rsMetaData.columnCount
            val receivedColumnNames = ArrayList<String>()

            for (i in 1 until numberOfColumns + 1)
                receivedColumnNames.add(rsMetaData.getColumnName(i))

            if (receivedColumnNames.contains("word")) word = rs.getString("word")
            if (receivedColumnNames.contains("root")) root = rs.getString("root")
            if (receivedColumnNames.contains("meaning")) meaning = rs.getString("meaning")
            if (receivedColumnNames.contains("partofspeech")) partOfSpeech = rs.getString("partofspeech")
            if (receivedColumnNames.contains("adddate")) date = rs.getDate("adddate")
            if (receivedColumnNames.contains("origin")) origin = rs.getString("origin")
            if (receivedColumnNames.contains("origin_lang")) originLang = rs.getString("origin_lang")

        } catch (e: Exception) {
            println("Error in getParticularWord: \n ${e.message}")
        }

        return Word(word, root, meaning, partOfSpeech, date, origin, originLang)
    }
}