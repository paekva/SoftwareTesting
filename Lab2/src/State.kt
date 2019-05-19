import database.Word

class State private constructor() {
    private object Holder { val INSTANCE = State() }
    companion object {
        val instance: State by lazy { Holder.INSTANCE }
    }

    private var lastQueryResult: List<Word> = arrayListOf()

    fun getLastResults(): List<Word>{
        return lastQueryResult
    }

    fun setLastResults(words: List<Word>){
        lastQueryResult = words
    }
}