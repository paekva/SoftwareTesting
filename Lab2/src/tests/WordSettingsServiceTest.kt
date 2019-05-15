package tests

import database.Word
import org.junit.Assert
import org.junit.Test
import services.WordSettingsService

class WordSettingsServiceTest {

    private val wss: WordSettingsService = WordSettingsService()

    @Test
    fun `add part of speech to a word`() {
        wss.setPartOfSpeech("переносица", "существительное")
        val word = wss.getWordInfo("переносица")
        Assert.assertEquals("существительное", word!!.getPartOfSpeech())
    }

    @Test
    fun `change origin of a word`() {
        val success = wss.changeWordOrigin(Word("парень", "пар", ""), "паря", "украинский")
        Assert.assertEquals(true, success)
    }

    @Test
    fun `try to change origin of a word not in dictionary`() {
        val success = wss.changeWordOrigin(Word("пар", "пар", ""), "паря", "украинский")
        Assert.assertEquals(false, success)
    }
}