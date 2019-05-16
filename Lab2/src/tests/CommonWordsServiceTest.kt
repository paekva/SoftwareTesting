package tests

import services.CommonWordsService
import org.junit.Test
import org.junit.Assert.*
import java.sql.Date
import java.time.LocalDate

class CommonWordsServiceTest {

    private val cws: CommonWordsService = CommonWordsService()

    @Test
    fun `getting list of common root words for a word, that is not in the dictionary `() {
        assertEquals(null, cws.getAllCommonRootWords(""))
    }

    @Test
    fun `getting list of common root words`() {
        val actual = cws.getAllCommonRootWords("нос")!!.map { el -> el.getWord() }
        val expected = listOf("носовой", "переносица")
        assertTrue(actual.containsAll(expected))
    }

    @Test
    fun `getting list of omonim root words for a word, that is not in the dictionary `() {
        assertEquals(null, cws.getAllOmonimRootWords(""))
    }

    @Test
    fun `getting list of omonim root words`() {
        val actual = cws.getAllOmonimRootWords("красавица")!!.map { el -> el.getWord() }
        val expected = listOf("краснеть", "краснощекий", "краситель", "раскраска", "красотка")
        assertTrue(actual.containsAll(expected))
    }

    @Test
    fun `group by word length`() {
        val words = cws.getAllCommonRootWords("занос")
        val actual = cws.groupBy(words!!, 7, 8).map { el -> el.getWord() }
        val expected = listOf("перенос", "обноски")
        assertTrue(actual.containsAll(expected))
    }

    @Test
    fun `group by partOfSpeech`() {
        val words = cws.getAllCommonRootWords("краснеть")
        val actual = cws.groupBy(words!!, "существительное").map { el -> el.getWord() }
        val expected = listOf("краснощекий")
        assertTrue(actual.containsAll(expected))
    }

    @Test
    //TODO: fix dates
    fun `group by word add date`() {
        val words = cws.getAllCommonRootWords("красотка")
        /*val actual = cws.groupBy(words, Date.parse("2019-05-14"),  Date.parse("2019-05-14")).map { el -> el.getWord() }
        actual.forEach{ el -> println(el)}
        val expected = listOf("красавица")
        assertTrue(actual.containsAll(expected))*/
    }
}