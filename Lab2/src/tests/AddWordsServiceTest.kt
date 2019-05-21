package tests

import database.Word
import org.junit.Assert.assertEquals
import org.junit.Ignore
import org.junit.Test
import services.AddWordsService
import services.CommonWordsService
import kotlin.test.assertFalse
import kotlin.test.assertTrue
import org.junit.runner.RunWith
import org.junit.runners.Suite


@RunWith(Suite::class)
@Suite.SuiteClasses(CommonWordsServiceTest::class,
    SelectionListServiceTest::class,
    WordSettingsServiceTest::class,
    DatabaseServiceTest::class)
class AddWordsServiceTest {

    private val aws: AddWordsService = AddWordsService()
    private val cws: CommonWordsService = CommonWordsService()

    @Test
    @Ignore
    fun `add a new word into the dictionary`() {
        val newWord = Word("краснощекий", "крас", "красный", "существительное")
        val success = aws.addWord(newWord)
        assertEquals(true, success)
    }

    @Test
    fun `add an existing word into the dictionary`() {
        val newWord = Word("красотка", "крас", "краса")
        val success = aws.addWord(newWord)
        assertEquals(false, success)
    }

    @Test
    @Ignore
    fun `add group of common root words into the dictionary`() {
        val group = arrayListOf(
            Word("краситель", "крас", "краска", "существительное"),
            Word("раскраска", "крас", "краска", "существительное"))
        val success = aws.addGroupOfWords(group)
        assertEquals(true, success)
    }

    @Test
    @Ignore
    fun `add pharse connected to word`() {
        val words = arrayListOf("раскраска", "больница")
        val phrase = "Я принес раскраску в больницу"
        aws.addPhrase(words, phrase)

        val actualFirst = cws.getAllPhrasesByWord("раскраска")
        assertTrue(actualFirst!!.contains(phrase))

        val actualSecond = cws.getAllPhrasesByWord("больница")
        assertTrue(actualSecond!!.contains(phrase))
    }

    @Test
    fun `add already added pharse`() {
        val words = arrayListOf("раскраска", "больница")
        val phrase = "Я принес раскраску в больницу"
        val success = aws.addPhrase(words, phrase)
        assertFalse(success)
    }
}