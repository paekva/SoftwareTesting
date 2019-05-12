import database.Word
import org.junit.Test
import org.junit.Assert.*
import org.junit.Ignore

class CommonWordsServiceTest {

    private val cws: CommonWordsService = CommonWordsService()

    @Test
    fun `cw service returns empty list for empty input`() {
        assertEquals(emptyList<Word>(), cws.getCommonWords(Word("", "")))
    }

    @Test
    fun `cw service returns list of results for input`() {
        val actual = cws.getCommonWords(Word("ехать", "ех")).map { el -> el.getWord() }
        val expected = listOf("приехать", "уехать", "заехать")
        assertTrue(actual.containsAll(expected))
    }

    @Test
    fun `add part of speech to a word`() {
        cws.setPartOfSpeech("приехать", "глагол")
        val word = cws.getWordInfo("приехать")
        assertEquals("глагол", word.getPartOfSpeech())
    }

    @Test
    @Ignore //need new word every time
    fun `add a word to the dictionary`() {
        val newWord = Word("задать", "да")
        val success = cws.addWord(newWord)
        assertEquals(true, success)
    }

    @Test
    @Ignore //need checking
    fun `add existing word to the dictionary`() {
        val newWord = Word("наехать", "ех")
        val success = cws.addWord(newWord)
        assertEquals(false, success)
    }
}