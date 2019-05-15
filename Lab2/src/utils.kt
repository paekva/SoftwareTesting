fun printSuccessMsg(msg: String){
    println("\u001B[32m $msg \u001B[0m ")
}

fun printErrorMsg(msg: String){
    println("\u001B[41m $msg \u001B[0m")
}

fun printInfoMsg(msg: String?){
    println("\u001B[36m $msg  \u001B[0m")
}