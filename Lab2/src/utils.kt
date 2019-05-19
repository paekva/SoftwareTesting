fun printMainMsg(msg: String){
    println("\u001B[32m$msg\u001B[0m ")
}

fun printErrorMsg(msg: String){
    println("\u001B[41m$msg\u001B[0m")
}

fun printSecondaryMsg(msg: String?){
    print("\u001B[36m$msg\u001B[0m")
}