package tests

import services.CommonWordsService
import database.Word
import org.junit.Test
import org.junit.Assert.*
import java.sql.Date
import java.time.LocalDate

class CommonWordsServiceTest {

    private val cws: CommonWordsService = CommonWordsService()

    @Test
    fun `cw service returns empty list for empty input`() {
        assertEquals(emptyList<Word>(), cws.getAllCommonRootWords(Word("", "", "")))
    }

    @Test
    fun `cw service returns list of results for input`() {
        val actual = cws.getAllCommonRootWords(Word("нос", "нос", "нос")).map { el -> el.getWord() }
        val expected = listOf("носовой", "переносица")
        assertTrue(actual.containsAll(expected))
    }

    @Test
    fun `group by word length`() {
        val words = cws.getAllCommonRootWords(Word("занос", "нос", "носить"))
        val actual = cws.groupBy(words, 7, 8).map { el -> el.getWord() }
        val expected = listOf("перенос", "обноски")
        assertTrue(actual.containsAll(expected))
    }

    @Test
    fun `group by partOfSpeech`() {
        val words = cws.getAllCommonRootWords(Word("краснеть", "крас", "красный"))
        val actual = cws.groupBy(words, "существительное").map { el -> el.getWord() }
        val expected = listOf("краснощекий")
        assertTrue(actual.containsAll(expected))
    }

    @Test
    //TODO: fix dates
    fun `group by word add date`() {
        val words = cws.getAllCommonRootWords(Word("красотка", "крас", "краса"))
        /*val actual = cws.groupBy(words, Date.parse("2019-05-14"),  Date.parse("2019-05-14")).map { el -> el.getWord() }
        actual.forEach{ el -> println(el)}
        val expected = listOf("красавица")
        assertTrue(actual.containsAll(expected))*/
    }
}