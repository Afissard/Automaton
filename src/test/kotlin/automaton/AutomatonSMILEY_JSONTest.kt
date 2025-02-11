package automaton

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class AutomatonSMILEY_JSONTest {
        private lateinit var smiley: Automaton

        @BeforeEach
        fun setUp(){
            smiley = Automaton.createAutomaton(AutomatonType.SMILEY_JSON)
        }

        @Test
        fun testGoodWord_1(){
            assertTrue(smiley.checkWord(":)"))
        }

        @Test
        fun testGoodWord_2(){
            assertTrue(smiley.checkWord(":-)"))
        }

        @Test
        fun testWordIncomplete(){
            assertThrows<IllegalStateException> {
                smiley.checkWord(":-")
            }
        }

        @Test
        fun testWordTooLong(){
            assertFalse(smiley.checkWord(":-):)"))
        }

        @Test
        fun testUnknowChar(){
            assertFalse(smiley.checkWord(":-/"))
        }

        @Test
        fun testAllPath(){
            assertAll(
                { assertTrue(smiley.checkWord(":)")) },
                { assertTrue(smiley.checkWord(":-)")) },
                { assertTrue(smiley.checkWord(":(")) },
                { assertTrue(smiley.checkWord(";-)")) },
                { assertTrue(smiley.checkWord(":=)")) },
                { assertTrue(smiley.checkWord("]-)")) }
            )
        }
    }