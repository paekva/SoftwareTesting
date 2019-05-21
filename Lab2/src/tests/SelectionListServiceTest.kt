package tests

import org.junit.Assert
import org.junit.Test
import services.SelectionListService

class SelectionListServiceTest {

    private val sls: SelectionListService = SelectionListService()

    @Test
    fun `get list of meanings for root`() {
        val expected = arrayListOf("краса", "краска", "красный")
        val actual = sls.getAvailableMeanings("крас")
        Assert.assertTrue(actual.containsAll(expected))
    }

    @Test
    fun `get empty list of meanings for root, that not presented`() {
        val expected = arrayListOf<String>()
        val actual = sls.getAvailableMeanings("кух")
        Assert.assertTrue(actual.containsAll(expected))
    }

    @Test
    fun `get list of part of speech`() {
        val expected = arrayListOf("глагол", "существительное", "прилагательное")
        val actual = sls.getAvailablePartsOfSpeech("")
        Assert.assertTrue(actual.containsAll(expected))
    }

    @Test
    fun `get list of origin languagies`() {
        val expected = arrayListOf("латынь")
        val actual = sls.getAvailableOriginLanguage("")
        Assert.assertTrue(actual.containsAll(expected))
    }
}