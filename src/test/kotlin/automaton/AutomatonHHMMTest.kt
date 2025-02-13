package automaton

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class AutomatonHHMMTest {
    private lateinit var hourAutomaton: Automaton

    @BeforeEach
    fun setUp() {
        hourAutomaton = Automaton("src/main/resources/hhmm.json")
    }

    @Test
    fun testValidTime_1() {
        assertTrue(hourAutomaton.checkWord("12:34"))
    }

    @Test
    fun testValidTime_2() {
        assertTrue(hourAutomaton.checkWord("00:00"))
    }

    @Test
    fun testInvalidTime_1() {
        assertFalse(hourAutomaton.checkWord("24:00"))
    }

    @Test
    fun testInvalidTime_2() {
        assertFalse(hourAutomaton.checkWord("12:60"))
    }

    @Test
    fun testIncompleteTime() {
        assertThrows<IllegalStateException> {
            hourAutomaton.checkWord("12:")
        }
    }

    @Test
    fun testTooLongTime() {
        assertFalse(hourAutomaton.checkWord("12:345"))
    }

    @Test
    fun testUnknownChar() {
        assertFalse(hourAutomaton.checkWord("12:3a"))
    }

    @Test
    fun testAllPath() {
        assertAll(
            { assertTrue(hourAutomaton.checkWord("01:23")) },
            { assertTrue(hourAutomaton.checkWord("10:45")) },
            { assertTrue(hourAutomaton.checkWord("23:59")) },
            { assertFalse(hourAutomaton.checkWord("25:00")) },
            { assertFalse(hourAutomaton.checkWord("12:61")) }
        )
    }
}