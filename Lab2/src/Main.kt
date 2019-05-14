import database.DatabaseConnection
import database.Word

fun main(){
    val dbc = DatabaseConnection()
    dbc.connect()
    dbc.findSameRootWords(Word("exaть", "ех", ""))
}