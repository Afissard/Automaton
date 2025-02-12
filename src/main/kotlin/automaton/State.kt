package automaton

/**
 * Class representing a state in the automaton.
 *
 * @property name The name of the state.
 */
class State(val name:String) {
    private val transitions:HashMap<Char, State> = hashMapOf()

    /**
     * Adds a transition from this state to another state on a given letter.
     *
     * @param toState The state to transition to.
     * @param letter The letter that triggers the transition.
     * @throws IllegalArgumentException if a transition for the given letter already exists.
     */
    fun addTransition(toState:State, letter:Char){
        require(!transitions.containsKey(letter)) {
            "'$letter' already have a transition from $name"
        }
        transitions[letter] = toState
    }

    /**
     * Gets the next state for a given letter.
     *
     * @param letter The letter to get the next state for.
     * @return The next state.
     * @throws NoSuchElementException if there is no transition for the given letter.
     */
    fun getNextState(letter: Char):State{
        if (!transitions.containsKey(letter)){
            // throw AutomatonExeption("$letter have no transition in this state")
            return transitions[' ']!!
        }
        return transitions[letter]!!
    }
}