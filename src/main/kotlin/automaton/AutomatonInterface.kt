package automaton

/**
 * Interface representing the basic operations of an automaton.
 */
interface AutomatonInterface {
    /**
     * Checks if the given word is accepted by the automaton.
     *
     * @param word The word to check.
     * @return True if the word is accepted, false otherwise.
     */
    fun checkWord(word : String) : Boolean

//    /**
//     * Generates a random word using the automaton.
//     *
//     * @return The randomly generated word.
//     */
//    fun generateRandomWord(): String

    /**
     * Generates a dot file representing the automaton.
     *
     * @param filePath The path to the dot file to generate.
     */
    fun generateDotFile(filePath: String)
}