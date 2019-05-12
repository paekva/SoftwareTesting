package tests

import services.CommonWordsService
import database.Word
import org.junit.Test
import org.junit.Assert.*
import org.junit.Ignore

class CommonWordsServiceTest {

    private val cws: CommonWordsService = CommonWordsService()

    @Test
    fun `cw service returns empty list for empty input`() {
        assertEquals(emptyList<Word>(), cws.getAllCommonRootWords(Word("", "")))
    }

    @Test
    fun `cw service returns list of results for input`() {
        val actual = cws.getAllCommonRootWords(Word("ехать", "ех")).map { el -> el.getWord() }
        val expected = listOf("приехать", "уехать", "заехать")
        assertTrue(actual.containsAll(expected))
    }

    @Test
    fun `add part of speech to a word`() {
        cws.setPartOfSpeech("приехать", "глагол")
        val word = cws.getWordInfo("приехать")
        assertEquals("глагол", word.getPartOfSpeech())
    }
}