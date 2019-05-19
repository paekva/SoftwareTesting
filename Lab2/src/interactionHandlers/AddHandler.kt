package interactionHandlers

import commandHandler
import database.Word
import handlerMock
import printErrorMsg
import printSecondaryMsg
import printMainMsg
import services.*
import java.util.*
import java.sql.Date as SQLDate

class AddHandler {
    private val uis: UserInteractionService = UserInteractionService()
    private val wss: WordSettingsService = WordSettingsService()
    private val aws: AddWordsService = AddWordsService()
    private val sls: SelectionListService = SelectionListService()
    private val msg = MessagingService.instance

    fun begin() {
        val uis = UserInteractionService()
        val availableCommandNumbers = 0..4
        val availableCommands = arrayOf<commandHandler>( ::addWord, ::addMultipleWords, ::addMultipleWordsToInputWord, ::addPhrase)

        var answerCode = -1
        while(answerCode != 0)
            answerCode = uis.getUserCommand(availableCommandNumbers, availableCommands, msg.getAddMenuMsg())
    }

    private fun addWord(){
        printMainMsg("Введите слово и всю необходимую информацию по нему")
        printSecondaryMsg("Вы можете пропустить необязательные к заполнению поля (обязательные поля помечены звездочкой *)\n" +
                "Чтобы прервать ввод нового слова, введите на любом шаге q. Внесенные вами изменения не сохранятся\n")

        val word = uis.getUserInput("* слово: ", false)
        if(word=="q") return

        val isInDictionary = wss.isWordInDictionary(word)
        if(isInDictionary) {
            printErrorMsg("Данное слово уже есть в словаре, попробуйте другую опцию программы!")
            return
        }

        val root = uis.getUserInput("* корень слова: ", false)
        if(root=="q") return

        val meaning = sls.menuWithDatabaseOptions(root, sls::getAvailableMeanings, "значение слова")
        if(meaning=="q") return

        val partOfSpeech = uis.getUserInput("часть речи: ", true)
        if(partOfSpeech=="q") return

        val origin = uis.getUserInput("слово, от которого оно произошло: ", true)
        if(origin=="q") return

        val originLang = uis.getUserInput("язык происхождения: ",true)
        if(originLang=="q") return

        val newWord = Word(word, root, meaning, partOfSpeech, SQLDate(Date().time), origin, originLang)
        val success = aws.addWord(newWord)

        if(success) printMainMsg("добавление слова $word прошло успешно")
        else printErrorMsg("произошла ошибка: слово $word не добавлено, попробуйте снова")
    }

    private fun addMultipleWords(){
        printMainMsg("Введите общую необходимую информацию по словам:")
        printSecondaryMsg("Вы можете пропустить необязательные к заполнению поля (обязательные поля помечены звездочкой *)\n")

        val root = uis.getUserInput("* корень слова: ", false)
        val meaning = sls.menuWithDatabaseOptions(root, sls::getAvailableMeanings, "значение слова")
        val success = getMultipleWordsInput(root, meaning)
        
        if(success) printMainMsg("добавление слов прошло успешно")
        else printErrorMsg("произошла ошибка: некоторые слова не были добавлены")
    }

    private fun addMultipleWordsToInputWord(){
        printMainMsg("Введите слово, к которому добавляются однокоренные:")
        printSecondaryMsg("Вы можете пропустить необязательные к заполнению поля (обязательные поля помечены звездочкой *)\n")

        val originalWordName = uis.getUserInput("* слова: ", false)
        val originalWord = wss.getWordInfo(originalWordName)

        if(originalWord == null){
            printErrorMsg("Данного слова нет в словаре, добавьте его или выберите другое")
            return
        }

        val success = getMultipleWordsInput(originalWord.getRoot(), originalWord.getMeaning())
        if(success) printMainMsg("добавление слов прошло успешно")
        else printErrorMsg("произошла ошибка: некоторые слова не были добавлены")
    }

    private fun addPhrase(){
        printMainMsg("Введите необходимую информацию:")
        printSecondaryMsg("Вы можете пропустить необязательные к заполнению поля (обязательные поля помечены звездочкой *)\n")

        val sentence = uis.getUserInput("* предложение: ", false)

        if(wss.isPhraseInDictionary(sentence)){
            printErrorMsg("Данная фраза уже существует в словаре. Попробйте другую опцию программы")
            return
        }

        var wordsNumber = uis.getNaturalNumber("Введите число слов, к которым вы хотите привязать данное предложение")
        val words = arrayListOf<String>()

        while(wordsNumber>0) {
            val word = uis.getUserInput("* слово $wordsNumber: ", false)
            val isInDictionary = wss.isWordInDictionary(word)
            if(!isInDictionary) {
                printErrorMsg("Данного слова нет в словаре")

                printSecondaryMsg("Вы можете:\n")
                val availableCommandNumbers = 0..2
                val availableCommands = arrayOf<commandHandler>( ::addWord, ::handlerMock)
                val answerCode = uis.getUserCommand(availableCommandNumbers, availableCommands, msg.getPhraseMenuMsg())

                if(answerCode == 0) return
                if(answerCode == 2) continue
            }

            words.add(word)
            wordsNumber--
        }

        val success = aws.addPhrase(words, sentence)
        if(success) printMainMsg("добавление фразы прошло успешно")
        else printErrorMsg("произошла ошибка: фраза не была добавлена")
    }

    private fun getMultipleWordsInput(root: String, meaning: String): Boolean{
        val newWords = arrayListOf<Word>()

        var wordsNumber = uis.getNaturalNumber("Введите число слов, которое вы хотите добавить")

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
}