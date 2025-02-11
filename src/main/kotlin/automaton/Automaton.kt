package automaton

open class Automaton protected constructor() : AutomatonInterface {
    protected lateinit var automatonData: AutomatonData
    private val states: HashMap<String, State> = hashMapOf()
    private lateinit var initialState:State
    private val finalStates:ArrayList<State> = arrayListOf()

    companion object {
        fun createAutomaton(automatonType: AutomatonType): Automaton {
            return when (automatonType) {
                AutomatonType.SMILEY -> AutomatonSMILEY()
                AutomatonType.SMILEY_JSON -> AutomatonCUSTOM("src/main/resources/smileyAutomaton.json")
                AutomatonType.ABC -> AutomatonCUSTOM("src/main/resources/abcAutomaton.json")
                AutomatonType.DATE -> TODO()
                AutomatonType.EMAIL -> TODO()
                AutomatonType.CUSTOM -> TODO()
            }
        }
    }

    override fun initFromAutomatonData() {
        // states
        for (state in automatonData.states) {
            if (states.containsKey(state)) {
                throw IllegalArgumentException("$state already exists")
            }
            states[state] = State(state)
        }

        // transitions
        for (transition in automatonData.transitions) {
            if (!states.containsKey(transition.from)) {
                throw IllegalArgumentException("${transition.from} does not exist")
            } else if (!states.containsKey(transition.to)) {
                throw IllegalArgumentException("${transition.to} does not exist")
            } else if (!automatonData.alphabet.contains(transition.letter)) {
                throw IllegalArgumentException("${transition.letter} is not defined in the alphabet")
            }
            states[transition.from]!!.addTransition(states[transition.to]!!, transition.letter)
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
    }

    override fun checkWord(word: String): Boolean {
        var currentState = initialState
        var currentCharIndex = 0

        while (!finalStates.contains(currentState) && states.containsValue(currentState) && currentCharIndex < word.length) {
            currentState = currentState.getNextState(word[currentCharIndex])
            println("${currentState.name} ${word[currentCharIndex]}")
            currentCharIndex ++
        }

        if (!finalStates.contains(currentState)) {
            throw AutomatonExeption("${currentState.name} is not a final state")
        }
        if (currentCharIndex < word.length) {
            throw AutomatonExeption("${word[currentCharIndex]} is not the last character but the automaton reach a final state")
        }

        return true
    }
}