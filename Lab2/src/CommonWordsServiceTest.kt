import database.Word
import org.junit.Test
import org.junit.Assert.*

class CommonWordsServiceTest {

    private val cws: CommonWordsService = CommonWordsService()

    @Test
    fun `cw service returns empty list for empty input`() {
        assertEquals(emptyList<Word>(), cws.getCommonWords(Word("", "")))
    }

    @Test
    fun `cw service returns list of results for input`() {
        val actual = cws.getCommonWords(Word("ехать", "ех")).map { el -> el.getWord() }
        val expected = listOf<String>("приехать", "уехать", "заехать")
        assertArrayEquals(expected.toTypedArray(), actual.toTypedArray())
    }

    @Test
    fun `add part of speech to a word`() {
        cws.setPartOfSpeech("приехать", "глагол")
        val word = cws.getWordInfo("приехать")
        assertEquals("глагол", word.getPartOfSpeech())
    }
}