package interactionHandlers

import commandHandler
import database.Word
import handlerMock
import printErrorMsg
import printInfoMsg
import printSuccessMsg
import services.AddWordsService
import services.SelectionListService
import services.UserInteractionService
import services.WordSettingsService
import java.util.*
import java.sql.Date as SQLDate

class AddHandler {
    private val mainMsg = "0. возврат в главное меню " +
            "\n1. добавить слово" +
            "\n2. добавить группу однокоренных слов" +
            "\n3. добавить группу слов, однокоренных к заданному" +
            "\n4. добавить предложение - пример к словам из словаря\n"

    private val uis: UserInteractionService = UserInteractionService()
    private val wss: WordSettingsService = WordSettingsService()
    private val aws: AddWordsService = AddWordsService()
    private val sls: SelectionListService = SelectionListService()

    fun begin(): Unit {
        val uis = UserInteractionService()
        val availableCommandNumbers = 1..4
        val availableCommands = arrayOf<commandHandler>( ::addNewWord, ::addNewWordGroup, ::addWordGroupToChoosenWord, ::addNewSentenceExample)

        uis.getUserCommand(availableCommandNumbers, availableCommands, mainMsg)
    }

    private fun addNewWord(){
        printSuccessMsg("Введите слово и всю необходимую информацию по нему:")
        printInfoMsg("Вы можете пропустить необязательные к заполнению поля (обязательные поля помечены звездочкой *)")
        println()
        printInfoMsg("Чтобы прервать ввод нового слова, введите на любом шаге q. Внесенные вами изменения не сохранятся")
        println()

        val word = uis.getUserInput("* слово: ", false)
        if(word=="q") return

        val isInDictionary = wss.isWordInDictionary(word)
        if(isInDictionary) {
            printErrorMsg("Данное слово уже есть в словаре, попробуйте другую опцию программы!")
            return
        }

        val root = uis.getUserInput("* корень слова: ", false)
        if(root=="q") return

        printInfoMsg("значение слова: ")
        val meaning = sls.menuWithDatabaseOptions(root, sls::getAvailableMeanings)
        if(meaning=="q") return

        val partOfSpeech = uis.getUserInput("часть речи: ", true)
        if(partOfSpeech=="q") return

        val origin = uis.getUserInput("слово, от которого оно произошло: ", true)
        if(origin=="q") return

        val originLang = uis.getUserInput("язык происхождения: ",true)
        if(originLang=="q") return

        val newWord = Word(word, root, meaning, partOfSpeech, SQLDate(Date().time), origin, originLang)
        val success = aws.addWord(newWord)

        if(success) printSuccessMsg("добавление слова $word прошло успешно")
        else printErrorMsg("произошла ошибка: слово $word не добавлено, попробуйте снова")
    }

    private fun addNewWordGroup(){
        printSuccessMsg("Введите общую необходимую информацию по словам:")
        printInfoMsg("Вы можете пропустить необязательные к заполнению поля (обязательные поля помечены звездочкой *)")
        println()

        val root = uis.getUserInput("* корень слова: ", false)

        val meaning = sls.menuWithDatabaseOptions(root, sls::getAvailableMeanings)

        val success = getMultipleWordsInput(root, meaning)
        if(success) printSuccessMsg("добавление слов прошло успешно")
        else printErrorMsg("произошла ошибка: некоторые слова не были добавлены")
    }

    private fun addWordGroupToChoosenWord(){
        printSuccessMsg("Введите слово, к которому добавляются однокоренные:")
        printInfoMsg("Вы можете пропустить необязательные к заполнению поля (обязательные поля помечены звездочкой *)")
        println()

        val originalWordName = uis.getUserInput("* слова: ", false)
        val originalWord = wss.getWordInfo(originalWordName)

        if(originalWord == null){
            printErrorMsg("Данного слова нет в словаре, добавьте его или выберите другое")
            return
        }

        val success = getMultipleWordsInput(originalWord.getRoot(), originalWord.getMeaning())
        if(success) printSuccessMsg("добавление слов прошло успешно")
        else printErrorMsg("произошла ошибка: некоторые слова не были добавлены")
    }

    private fun addNewSentenceExample(){
        printSuccessMsg("Введите необходимую информацию:")
        printInfoMsg("Вы можете пропустить необязательные к заполнению поля (обязательные поля помечены звездочкой *)")
        println()

        val sentence = uis.getUserInput("* предложение: ", false)
        var wordsNumber = getNumberOfWordsForInput("Введите число слов, к которым вы хотите привязать данное предложение")
        val words = arrayListOf<String>()

        while(wordsNumber>0) {
            val word = uis.getUserInput("* слово $wordsNumber: ", false)
            val isInDictionary = wss.isWordInDictionary(word)
            if(isInDictionary) {
                wordsNumber--
                continue
            }

            printErrorMsg("Данного слова нет в словаре")
            val msg = "Вы можете: \n" +
                    "0. отменить добавление предложения\n" +
                    "1. добавить это слово в словарь\n" +
                    "2. ввести другое слово\n"
            val availableCommandNumbers = 1..2
            val availableCommands = arrayOf<commandHandler>( ::addNewWord, ::handlerMock)
            val answerCode = uis.getUserCommand(availableCommandNumbers, availableCommands, msg)

            if(answerCode == 0) return
            if(answerCode == 2) continue

            words.add(word)
            wordsNumber--
        }

        val success = aws.addPhrase(words, sentence)
        if(success) printSuccessMsg("добавление слов прошло успешно")
        else printErrorMsg("произошла ошибка: некоторые слова не были добавлены")
    }

    private fun getMultipleWordsInput(root: String, meaning: String): Boolean{
        val newWords = arrayListOf<Word>()

        var wordsNumber = getNumberOfWordsForInput("Введите число слов, которое вы хотите добавить")

        while(wordsNumber>0){
            val word = uis.getUserInput("* слово $wordsNumber: ", false)
            val isInDictionary = wss.isWordInDictionary(word)
            if(isInDictionary) {
                printErrorMsg("Данное слово уже есть в словаре, попробуйте другую опцию программы!")
                continue
            }
            val partOfSpeech = uis.getUserInput("часть речи: ", true)
            val origin = uis.getUserInput("слово, от которого оно произошло: ", true)
            val originLang = uis.getUserInput("язык происхождения: ",true)

            val newWord = Word(word, root, meaning, partOfSpeech, SQLDate(Date().time), origin, originLang)
            newWords.add(newWord)
            wordsNumber--
        }

        return aws.addGroupOfWords(newWords)
    }

    private fun getNumberOfWordsForInput(msg: String): Int {
        val reader = Scanner(System.`in`)
        printInfoMsg(msg)

        var wordsNumber = reader.nextInt()
        while(wordsNumber<1){
            wordsNumber = reader.nextInt()
            if(wordsNumber<1)
                printErrorMsg("Неверный ввод, попробуйте еще раз")
        }
        return wordsNumber
    }
}