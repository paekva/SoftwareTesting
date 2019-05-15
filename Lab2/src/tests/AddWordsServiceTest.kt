package tests

import database.Word
import org.junit.Assert.assertEquals
import org.junit.Ignore
import org.junit.Test
import services.AddWordsService

class AddWordsServiceTest {

    private val aws: AddWordsService = AddWordsService()

    @Test
    @Ignore
    fun `add a new word into the dictionary`() {
        val newWord = Word("краснощекий", "крас", "красный", "существительное")
        val success = aws.addWord(newWord)
        assertEquals(true, success)
    }

    @Test
    fun `add existing word into the dictionary`() {
        val newWord = Word("красотка", "крас", "краса")
        val success = aws.addWord(newWord)
        assertEquals(false, success)
    }

    @Test
    fun `get meanings examples by root`() {
        aws.getMeanings("крас")
        // assertEquals(false, success)
    }

    @Test
    @Ignore
    fun `add group of common root words into the dictionary`() {
        val group = arrayListOf<Word>(
            Word("краситель", "крас", "краска", "существительное"),
            Word("раскраска", "крас", "краска", "существительное"))
        val success = aws.addGroupOfWords(group)
        assertEquals(true, success)
    }

    @Test
    @Ignore //TODO: finish functionality properly
    fun `add pharse connected to word`() {
        val word = Word("наехать", "ех", "")
        val phrase = "Наеxaть на столб в темноте оказалось легко"
        val success = aws.addPhrase(word, phrase)
        assertEquals(true, success)
    }
}