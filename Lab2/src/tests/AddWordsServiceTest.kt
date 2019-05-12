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
        val newWord = Word("кровать", "кров")
        val success = aws.addWord(newWord)
        assertEquals(true, success)
    }

    @Test
    fun `add existing word into the dictionary`() {
        val newWord = Word("наехать", "ех")
        val success = aws.addWord(newWord)
        assertEquals(false, success)
    }

    @Test
    @Ignore
    fun `add group of common root words into the dictionary`() {
        val group = arrayListOf<Word>(Word("думать", "дум"), Word("обдумать", "дум"))
        val success = aws.addGroupOfWords(group)
        assertEquals(true, success)
    }

}