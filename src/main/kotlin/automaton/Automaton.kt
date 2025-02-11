package automaton

open class Automaton protected constructor() : AutomatonInterface {
    protected lateinit var automatonData: AutomatonData
    private val states: HashMap<String, State> = hashMapOf()
    private lateinit var initialState:State
    private var finalStates:ArrayList<State> = arrayListOf()
    private val errorChar: Char = ' '

    companion object {
        fun createAutomaton(automatonType: AutomatonType): Automaton {
            return when (automatonType) {
                AutomatonType.SMILEY -> AutomatonSMILEY()
                AutomatonType.SMILEY_JSON -> AutomatonCUSTOM("src/main/resources/smileyJson.json")
                AutomatonType.ABC -> AutomatonCUSTOM("src/main/resources/abcAutomaton.json")
                AutomatonType.HOUR -> AutomatonCUSTOM("src/main/resources/hhmm.json")
                AutomatonType.DATE -> AutomatonCUSTOM("src/main/resources/ddmmyyyy.json")
                AutomatonType.CUSTOM -> TODO()
            }
        }
    }

    override fun initFromAutomatonData() {
        // add error state
        automatonData.states.add("error")

        // states
        for (state in automatonData.states) {
            if (states.containsKey(state)) {
                throw IllegalArgumentException("$state already exists")
            }
            states[state] = State(state)
            if (state != "error") {
                automatonData.transitions.add(TransitionData(state, "error", arrayListOf(errorChar)))
            }
        }

        // transitions
        for (transition in automatonData.transitions) {
            require(states.containsKey(transition.from)) {"${transition.from} does not exist"}
            require(states.containsKey(transition.to)) {"${transition.to} does not exist"}
            for (label in transition.label) {
                require(automatonData.alphabet.contains(label) || label == errorChar) {"$label is not defined in the alphabet"}
                states[transition.from]!!.addTransition(states[transition.to]!!, label)
            }
        }

        // initial state
        if (!states.containsKey(automatonData.initialState)) {
            throw AutomatonExeption("No initial state defined")
        }
        initialState = states[automatonData.initialState]!!

        // final state
        for (s in automatonData.finalStates) {
            if (!states.containsKey(s)){
                throw AutomatonExeption("$s is not defined as a state")
            }
            finalStates.add(states[s]!!)
        }
        finalStates.add(states["error"]!!)
    }

    override fun checkWord(word: String): Boolean {
        var currentState = initialState
        var currentCharIndex = 0

        while (!finalStates.contains(currentState) && states.containsValue(currentState) && currentCharIndex < word.length) {
            currentState = currentState.getNextState(word[currentCharIndex])
            println("${currentState.name} ${word[currentCharIndex]}")
            currentCharIndex ++
        }

        check(finalStates.contains(currentState)) { "${currentState.name} is not a final state" }

        if (currentCharIndex < word.length) {
            //throw AutomatonExeption("${word[currentCharIndex]} is not the last character but the automaton reach a final state")
            return false
        }
        if (currentState.name == "error") {
            return false
        }

        return true
    }
}