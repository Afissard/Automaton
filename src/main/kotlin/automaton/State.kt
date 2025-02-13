package automaton

/**
 * Class representing a state in the automaton.
 *
 * @property name The name of the state.
 */
class State(val name:String): StateInterface {
    private val transitions:HashMap<Char, State> = hashMapOf()

    /**
     * Adds a transition from this state to another state on a given letter.
     *
     * @param toState The state to transition to.
     * @param letter The letter that triggers the transition.
     * @throws IllegalArgumentException if a transition for the given letter already exists.
     */
    override fun addTransition(toState:State, letter:Char){
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
    override fun getNextState(letter: Char):State{
        if (!transitions.containsKey(letter)){
            // throw AutomatonExeption("$letter have no transition in this state")
            return transitions[' ']!!
        }
        return transitions[letter]!!
    }


    /**
     * Gets a random next state from the current state.
     *
     * @return A pair containing the character that triggers the transition and the next state.
     * If there are no transitions or only a transition with the space character, it returns the space character and the corresponding state.
     */
    override fun getRandomNextState(): Pair<Char, State> {
        // if the only next state is error then return error state
        if (transitions.isEmpty() || transitions.size == 1 && transitions.containsKey(' ')){
            return Pair(' ', transitions[' ']!!)
        }

        // because there is another state available, the error state is locally removed
        val keys = transitions.keys
        if (keys.contains(' ')) {
            keys.remove(' ')
        }
        val randomIndex = (0 until keys.size).random()
        val chosenTransition = transitions[keys.elementAt(randomIndex)]!!
        return Pair(keys.elementAt(randomIndex), chosenTransition)
    }
}