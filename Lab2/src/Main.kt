import database.DataBaseConnection
import database.Word

fun main(){
    val dbc = DataBaseConnection()
    dbc.connect()
    dbc.findSameRootWords(Word("exaть", "ех"))
}

