package tests

import database.DatabaseService
import database.Word
import org.junit.Assert
import org.junit.Test
import java.sql.Date

class DatabaseServiceTest {

    private val dbs: DatabaseService = DatabaseService()

    @Test
    fun `getting empty list for empty input`() {
        Assert.assertEquals(emptyList<Word>(), dbs.findSameRootWords(Word("", "", "")))
    }

    @Test
    fun `getting list of common root words`() {
        val actual = dbs.findSameRootWords(Word("краснощекий", "крас", "красный")).map { el -> el.getWord() }
        val expected = listOf("краснеть")
        Assert.assertTrue(actual.containsAll(expected))
    }

    @Test
    fun `add part of speech to a word`() {
        dbs.changePartOfSpeech("краснеть", "глагол")
        val word = dbs.getParticularWord("краснеть")
        Assert.assertEquals("глагол", word!!.getPartOfSpeech())
    }

    @Test
    fun `get the word from the dictionary`() {
        val actual = dbs.getParticularWord("краснеть")
        val expected = Word("краснеть", "крас", "красный", "существительное", Date(2019, 5, 15))
        Assert.assertEquals(expected, actual)
    }

    @Test
    fun `get the null for the word that is not in the dictionary`() {
        val actual = dbs.getParticularWord("абракодабра")
        val expected = null
        Assert.assertEquals(expected, actual)
    }
}