package automaton

class AutomatonSMILEY() : Automaton() {

    init {
        automatonData = AutomatonData(
            alphabet = arrayListOf(';', ':', ']', '(', ')', '-', '='),
            states = arrayListOf("e1", "e2", "e3", "e4", "e5"),
            initialState = "e1",
            finalStates = arrayListOf("e5"),
            transitions = arrayListOf(
                TransitionData("e1", "e2", ':'),
                TransitionData("e1", "e3", ';'),
                TransitionData("e1", "e3", ']'),
                TransitionData("e2", "e5", '('),
                TransitionData("e2", "e5", ')'),
                TransitionData("e2", "e4", '-'),
                TransitionData("e2", "e4", '='),
                TransitionData("e3", "e4", '-'),
                TransitionData("e4", "e5", ')')
            )
        )

        initFromAutomatonData()
    }
}
