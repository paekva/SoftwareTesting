package Utils

fun printSuccessMsg(testName: String){
    println("\u001B[32m $testName was SUCCESSFUL \u001B[0m ")
}

fun printErrorMsg(testName: String, msg: String?){
    println("\u001B[41m $testName FAILED due to \u001B[0m \n\t $msg \n")
}

fun printInfoMsg(msg: String?){
    println("\u001B[36m $msg \n")
}