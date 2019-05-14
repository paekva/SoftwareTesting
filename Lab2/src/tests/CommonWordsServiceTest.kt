package tests

import services.CommonWordsService
import database.Word
import org.junit.Test
import org.junit.Assert.*
import java.sql.Date

class CommonWordsServiceTest {

    private val cws: CommonWordsService = CommonWordsService()

    @Test
    fun `cw service returns empty list for empty input`() {
        assertEquals(emptyList<Word>(), cws.getAllCommonRootWords(Word("", "", "")))
    }

    @Test
    fun `cw service returns list of results for input`() {
        val actual = cws.getAllCommonRootWords(Word("ехать", "ех", "")).map { el -> el.getWord() }
        val expected = listOf("приехать", "уехать", "заехать")
        assertTrue(actual.containsAll(expected))
    }

    @Test
    fun `group by word length`() {
        val words = cws.getAllCommonRootWords(Word("ехать", "ех", ""))
        val actual = cws.groupBy(words, 6, 7).map { el -> el.getWord() }
        val expected = listOf("наехать", "уехать", "заехать")
        assertTrue(actual.containsAll(expected))
    }

    @Test
    fun `group by partOfSpeech`() {
        val words = cws.getAllCommonRootWords(Word("ехать", "ех", ""))
        words.forEach { el -> print(el.getPartOfSpeech()) }
        val actual = cws.groupBy(words, "глагол").map { el -> el.getWord() }
        val expected = listOf("приехать")
        print(actual)
        actual.forEach { el -> print(el) }
        //assertTrue(actual.containsAll(expected))
    }

    @Test
    fun `group by word date`() {
        val words = cws.getAllCommonRootWords(Word("ехать", "ех", ""))
        val actual = cws.groupBy(words, Date(11, 5, 2019), Date(11, 5, 2019)).map { el -> el.getWord() }
        //val expected = listOf("наехать", "уехать", "заехать")
        //assertTrue(actual.containsAll(expected))
    }
}