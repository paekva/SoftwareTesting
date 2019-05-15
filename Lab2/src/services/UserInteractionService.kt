package services

import database.Word

class UserInteractionService {

    fun textInputField(value: String): Boolean{
        return value.toLowerCase().matches("[a-z]+".toRegex())
    }

    fun commandInputField(value: Int, interval: IntRange): Boolean{
        return value in interval
    }

    fun displayCommonRootWords(words: List<Word>) : List<String>{
        return words.map { word -> word.getWord() }
    }

    fun displayWordInfo(word: Word): String{
        return "СЛОВО ${word.getWord()}\n" +
                "КОРЕНЬ ${word.getRoot()}\n" +
                "ДАТА ДОБАВЛЕНИЯ ${word.getDate()}\n" +
                "ЧАСТЬ РЕЧИ ${word.getPartOfSpeech()}\n"
    }
}