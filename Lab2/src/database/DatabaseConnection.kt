package database

import java.sql.*
import java.util.ArrayList

class DatabaseConnection {
    internal val DB_URL = "jdbc:postgresql://127.0.0.1:5432/dictionary"
    internal val USER = "postgres"
    internal val PASS = "rfnz98grf"

    private var connection: Connection? = null

    @Throws(SQLException::class)
    internal fun connect() {
        connection = DriverManager
            .getConnection(DB_URL, USER, PASS)
    }

    internal fun insert(SQL: String, args: List<String>) {
        try {
            val pstmt = connection!!.prepareStatement(SQL)
            for (i in args.indices) {
                pstmt.setString(i + 1, args[i])
            }
            pstmt.executeUpdate()
        } catch (ex: SQLException) {
            println("An error in insert: \n ${ex.message}")
        }

    }

    internal fun select(SQL: String, args: List<String>): ResultSet? {
        try {
            val pstmt = connection!!.prepareStatement(SQL)
            for (i in args.indices) {
                pstmt.setString(i + 1, args[i])
            }

            //println(pstmt)
            return pstmt.executeQuery()
        } catch (e: Exception) {
            println("Error in select:\n ${e.message}")
            return null
        }

    }

    internal fun getWord(rs: ResultSet): Word {
        var word = ""
        var root = ""
        var meaning = ""
        var partOfSpeech = ""
        var date: Date = Date(1)
        try {
            val rsMetaData = rs.metaData
            val numberOfColumns = rsMetaData.columnCount
            val receivedColumnNames = ArrayList<String>()

            for (i in 1 until numberOfColumns + 1) {
                receivedColumnNames.add(rsMetaData.getColumnName(i))
            }

            if (receivedColumnNames.contains("word")) word = rs.getString("word")
            if (receivedColumnNames.contains("root")) root = rs.getString("root")
            if (receivedColumnNames.contains("meaning")) meaning = rs.getString("meaning")
            if (receivedColumnNames.contains("partofspeech")) partOfSpeech = rs.getString("partofspeech")
            if (receivedColumnNames.contains("adddate")) date = rs.getDate("adddate")
        } catch (e: Exception) {
            println("Error in getParticularWord: \n ${e.message}")
        }

        return Word(word, root, meaning, partOfSpeech, date)
    }
}