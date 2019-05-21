package tests

import org.junit.Assert
import org.junit.Test
import services.WordSettingsService

class WordSettingsServiceTest {

    private val wss: WordSettingsService = WordSettingsService()

    @Test
    fun `add part of speech to a word`() {
        wss.setPartOfSpeech("больничный", "существительное")
        val word = wss.getWordInfo("больничный")
        Assert.assertEquals("существительное", word!!.getPartOfSpeech())
    }

    @Test
    fun `change origin of a word`() {
        val success = wss.changeWordOrigin("больничный", "бол")
        Assert.assertTrue(success)

        val word = wss.getWordInfo("больничный")
        Assert.assertEquals("бол", word!!.getOrigin())
    }

    @Test
    fun `change origin language of a word`() {
        val success = wss.changeWordOriginLanguage("больничный", "латынь")
        Assert.assertTrue(success)

        val word = wss.getWordInfo("больничный")
        Assert.assertEquals("латынь", word!!.getOriginLang())
    }

    @Test
    fun `looks for word, which is in the dictionary`() {
        val word = wss.isPhraseInDictionary("краска")
        Assert.assertNotNull(word)
    }

    @Test
    fun `looks for word, which is not in the dictionary`() {
        val word = wss.getWordInfo("высохнуть")
        Assert.assertNull(word)
    }


    @Test
    fun `checks that word is in the dictionary`() {
        val success = wss.isWordInDictionary("краска")
        Assert.assertTrue(success)
    }

    @Test
    fun `checks that word is not in the dictionary`() {
        val success = wss.isWordInDictionary("высохнуть")
        Assert.assertFalse(success)
    }

    @Test
    fun `checks that phrase is in the dictionary`() {
        val success = wss.isPhraseInDictionary("Краска высохла")
        Assert.assertTrue(success)
    }

    @Test
    fun `checks that phrase is not in the dictionary`() {
        val success = wss.isPhraseInDictionary("Краска не высохла")
        Assert.assertFalse(success)
    }
}