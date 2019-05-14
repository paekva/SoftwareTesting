package services

import database.Word

class UserInteractionService {

    fun textInputField(value: String): Boolean{
        return value.toLowerCase().matches("[a-z]+".toRegex())
    }

    fun commandInputField(value: String): Boolean{
        return value.matches("[1-5]".toRegex())
    }

    fun displayCommonRootWords(words: List<Word>) : List<String>{
        return words.map { word -> word.getWord() }
    }

    fun displayWordInfo(word: Word): String{
        return "СЛОВО ${word.getWord()}\n" +
                "КОРЕНЬ ${word.getRoot()}\n" +
                "ДАТА ДОБАВЛЕНИЯ ${word.getDate()}\n" +
                "ЧАСТЬ РЕЧИ ${word.getPartOfSpeech()}\n" +
                "КОЛ-ВО ПОИСКОВЫХ ЗАПРОСОВ ПО СЛОВУ ${word.getSearched()}\n"
    }
}