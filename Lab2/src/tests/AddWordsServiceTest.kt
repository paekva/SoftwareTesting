package tests

import database.Word
import org.junit.Assert.assertEquals
import org.junit.Ignore
import org.junit.Test
import services.AddWordsService

class AddWordsServiceTest {

    private val aws: AddWordsService = AddWordsService()

    @Test
    @Ignore
    fun `add a new word into the dictionary`() {
        val newWord = Word("красавица", "крас", "краса", "существительное")
        val success = aws.addWord(newWord)
        assertEquals(true, success)
    }

    @Test
    fun `add existing word into the dictionary`() {
        val newWord = Word("наехать", "ех", "")
        val success = aws.addWord(newWord)
        assertEquals(false, success)
    }

    @Test
    fun `get meanings examples by root`() {
        val success = aws.getMeanings("нос")
        // assertEquals(false, success)
    }

    @Test
    @Ignore
    fun `add group of common root words into the dictionary`() {
        val group = arrayListOf<Word>(Word("думать", "дум", ""), Word("обдумать", "дум", ""))
        val success = aws.addGroupOfWords(group)
        assertEquals(true, success)
    }

    @Test
    fun `add pharse connected to word`() {
        val word = Word("наехать", "ех", "")
        val phrase = "Наеxaть на столб в темноте оказалось легко"
        val success = aws.addPhrase(word, phrase)
        assertEquals(true, success)
    }
}