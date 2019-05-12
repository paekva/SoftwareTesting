package tests

import services.CommonWordsService
import database.Word
import org.junit.Test
import org.junit.Assert.*

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
}