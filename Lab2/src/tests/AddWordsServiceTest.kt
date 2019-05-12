package tests

import database.Word
import org.junit.Assert
import org.junit.Assert.assertEquals
import org.junit.Test
import services.AddWordsService

class AddWordsServiceTest {

    private val aws: AddWordsService = AddWordsService()

    @Test
    fun `add a new word to the dictionary`() {
        val newWord = Word("отдать", "да")
        val success = aws.addWord(newWord)
        assertEquals(true, success)
    }

    @Test
    fun `add existing word to the dictionary`() {
        val newWord = Word("наехать", "ех")
        val success = aws.addWord(newWord)
        assertEquals(false, success)
    }


}