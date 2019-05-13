package tests

import org.junit.Assert
import org.junit.Test
import services.UserInteractionService
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class UserInteractionServiceTest {
    private val uis: UserInteractionService = UserInteractionService()

    @Test
    fun `text correct input check`() {
        val correctInput = uis.textInputField("word")
        assertTrue(correctInput)
    }

    @Test
    fun `text incorrect input check`() {
        val incorrectInput = uis.textInputField("fe/ 1")
        assertFalse(incorrectInput)
    }

    @Test
    fun `command correct input check`() {
        val correctInput = uis.commandInputField("3")
        assertTrue(correctInput)
    }

    @Test
    fun `command incorrect input check`() {
        val incorrectInput = uis.commandInputField("d")
        assertFalse(incorrectInput)
    }
}