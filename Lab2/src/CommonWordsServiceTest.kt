import database.Word
import org.junit.Test
import kotlin.test.assertEquals

class CommonWordsServiceTest {

    private val cws: CommonWordsService = CommonWordsService()

    @Test
    fun `cw service returns empty list for empty input`() {
        assertEquals(emptyList<String>(), cws.getCommonWords(Word("", "")))
    }

    @Test
    fun `cw service returns list of results for input`() {
        assertEquals(listOf<String>("приехать", "уехать", "заехать"), cws.getCommonWords(Word("ехать", "ех")))
    }
}