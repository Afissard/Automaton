package automaton

interface StateInterface {

    /**
     * Adds a transition from this state to another state on a given letter.
     *
     * @param toState The state to transition to.
     * @param letter The letter that triggers the transition.
     * @throws IllegalArgumentException if a transition for the given letter already exists.
     */
    fun addTransition(toState:State, letter:Char)

    /**
     * Gets the next state for a given letter.
     *
     * @param letter The letter to get the next state for.
     * @return The next state.
     * @throws NoSuchElementException if there is no transition for the given letter.
     */
    fun getNextState(letter: Char):State

    /**
     * Gets a random next state from the current state.
     *
     * @return A pair containing the character that triggers the transition and the next state.
     * If there are no transitions or only a transition with the space character, it returns the space character and the corresponding state.
     */
    fun getRandomNextState(): Pair<Char, State>
}