package automaton

/**
 * Class representing an automaton for recognizing smiley faces.
 */
class AutomatonSMILEY() : Automaton() {

    /**
     * Initializes the automaton with predefined states, alphabet, and transitions.
     */
    init {
        automatonData = AutomatonData(
            alphabet = arrayListOf(';', ':', ']', '(', ')', '-', '='),
            states = arrayListOf("e1", "e2", "e3", "e4", "e5"),
            initialState = "e1",
            finalStates = arrayListOf("e5"),
            transitions = arrayListOf(
                TransitionData("e1", "e2", arrayListOf(':')),
                TransitionData("e1", "e3", arrayListOf(';', ']')),
                TransitionData("e2", "e5", arrayListOf('(', ')')),
                TransitionData("e2", "e4", arrayListOf('-', '=')),
                TransitionData("e3", "e4", arrayListOf('-')),
                TransitionData("e4", "e5", arrayListOf(')'))
            )
        )

        initFromAutomatonData()
    }
}
