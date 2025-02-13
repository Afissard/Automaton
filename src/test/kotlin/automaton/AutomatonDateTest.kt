package automaton

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class AutomatonDateTest {
    private lateinit var dateAutomaton: Automaton

    @BeforeEach
    fun setUp() {
        dateAutomaton = Automaton("./src/main/resources/ddmmyyyy.json")
    }

    @Test
    fun testValidDate_1() {
        assertTrue(dateAutomaton.checkWord("01/01/2023"))
    }

    @Test
    fun testValidDate_2() {
        assertTrue(dateAutomaton.checkWord("28/02/2023"))
    }

    @Test
    fun testValidDate_3() {
        assertTrue(dateAutomaton.checkWord("31/12/9999"))
    }

    @Test
    fun testInvalidDate_1() {
        assertFalse(dateAutomaton.checkWord("32/01/2023"))
    }

    @Test
    fun testInvalidDate_2() {
        assertFalse(dateAutomaton.checkWord("29/02/2023"))
    }

    @Test
    fun testInvalidDate_3() {
        assertFalse(dateAutomaton.checkWord("31/04/2023"))
    }

    @Test
    fun testInvalidDate_4() {
        assertFalse(dateAutomaton.checkWord("00/01/2023"))
    }

    @Test
    fun testInvalidDate_5() {
        assertFalse(dateAutomaton.checkWord("01/13/2023"))
    }

    @Test
    fun testInvalidDate_6() {
        assertFalse(dateAutomaton.checkWord("01/01/0000"))
    }

    @Test
    fun testIncompleteDate() {
        assertThrows<IllegalStateException> {
            dateAutomaton.checkWord("01/01/")
        }
    }

    @Test
    fun testTooLongDate() {
        assertFalse(dateAutomaton.checkWord("01/01/20234"))
    }

    @Test
    fun testUnknownChar() {
        assertFalse(dateAutomaton.checkWord("01/01/20a3"))
    }

    @Test
    fun testAllPath() {
        assertAll(
            { assertTrue(dateAutomaton.checkWord("01/01/2023")) },
            { assertTrue(dateAutomaton.checkWord("15/06/2023")) },
            { assertTrue(dateAutomaton.checkWord("31/12/2023")) },
            { assertFalse(dateAutomaton.checkWord("32/01/2023")) },
            { assertFalse(dateAutomaton.checkWord("00/01/2023")) }
        )
    }
}