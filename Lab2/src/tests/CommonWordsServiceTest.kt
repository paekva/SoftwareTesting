package tests

import services.CommonWordsService
import org.junit.Test
import org.junit.Assert.*

class CommonWordsServiceTest {

    private val cws: CommonWordsService = CommonWordsService()

    @Test
    fun `getting list of common root words for a word, that is not in the dictionary `() {
        assertEquals(null, cws.getAllCommonRootWords("абракадабра"))
    }

    @Test
    fun `getting list of common root words`() {
        val actual = cws.getAllCommonRootWords("краска")!!.map { el -> el.getWord() }
        val expected = listOf("краситель", "раскраска")
        assertTrue(actual.containsAll(expected))
    }

    @Test
    fun `getting list of omonim root words for a word, that is not in the dictionary `() {
        assertEquals(null, cws.getAllOmonimRootWords("абракадабра"))
    }

    @Test
    fun `getting list of omonim root words`() {
        val actual = cws.getAllOmonimRootWords("красавица")!!.map { el -> el.getWord() }
        val expected = listOf("краска", "краснеть", "краснощекий", "краситель", "раскраска", "красотка", "красоваться")
        assertTrue(actual.containsAll(expected))
    }

    @Test
    fun `getting list of phrases for a word, that is not in the dictionary `() {
        assertEquals(null, cws.getAllPhrasesByWord("абракадабра"))
    }

    @Test
    fun `getting empty list of phrases for a word, that does not have any`() {
        assertEquals(arrayListOf<String>(), cws.getAllPhrasesByWord("краситель"))
    }

    @Test
    fun `getting list of phrases for word`() {
        val actual = cws.getAllPhrasesByWord("краска")
        val expected = listOf("Краска высохла")
        assertTrue(actual!!.containsAll(expected))
    }

    @Test
    fun `getting empty list of words for not added root`() {
        val actual = cws.getAllWordsByRoot("кот")
        assertEquals(arrayListOf<String>(), actual)
    }

    @Test
    fun `getting list of words for selected root`() {
        val actual = cws.getAllWordsByRoot("боль")!!.map { el -> el.getWord() }
        val expected = arrayListOf("боль", "больница", "больной", "больничный")
        assertTrue(actual.containsAll(expected))
    }

    @Test
    fun `getting empty list of words for not presented part of speech`() {
        val actual = cws.getAllWordsByPartOfSpeech("междометие")
        assertEquals(arrayListOf<String>(), actual)
    }

    @Test
    fun `getting list of words for selected part of speech`() {
        val actual = cws.getAllWordsByPartOfSpeech("глагол")!!.map{ el -> el.getWord()}
        val expected = arrayListOf("красоваться", "одомашнить", "краснеть")
        assertEquals(expected, actual)
    }

    @Test
    fun `group by word length`() {
        val words = cws.getAllOmonimRootWords("краска")
        val actual = cws.groupBy(words!!, 10, 11).map { el -> el.getWord() }
        val expected = listOf("красоваться", "краснощекий")
        assertTrue(actual.containsAll(expected))
    }

    @Test
    fun `group by partOfSpeech`() {
        val words = cws.getAllCommonRootWords("краснеть")
        val actual = cws.groupBy(words!!, "существительное").map { el -> el.getWord() }
        val expected = listOf("краснощекий")
        assertTrue(actual.containsAll(expected))
    }
}