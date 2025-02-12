package automaton

/**
 * Interface representing the basic operations of an automaton.
 */
interface AutomatonInterface {

    /**
     * Initializes the automaton from the provided automaton data.
     */
    fun initFromAutomatonData()

    /**
     * Checks if the given word is accepted by the automaton.
     *
     * @param word The word to check.
     * @return True if the word is accepted, false otherwise.
     */
    fun checkWord(word : String) : Boolean
}