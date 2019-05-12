package tests

import org.junit.Assert
import org.junit.Test
import services.WordSettingsService

class WordSettingsServiceTest {

    private val wss: WordSettingsService = WordSettingsService()

    @Test
    fun `add part of speech to a word`() {
        wss.setPartOfSpeech("приехать", "глагол")
        val word = wss.getWordInfo("приехать")
        Assert.assertEquals("глагол", word.getPartOfSpeech())
    }
}