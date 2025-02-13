package automaton

import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import java.io.File

/**
 * Class representing an automaton.
 *
 * @param filePath The path to the JSON file containing the automaton data.
 */
class Automaton(filePath: String): AutomatonInterface {
    private var automatonData: AutomatonData
    private val states: HashMap<String, State> = hashMapOf()
    private var initialState:State
    private var finalStates:ArrayList<State> = arrayListOf()
    private val errorChar: Char = ' '

    /**
     * Initializes the automaton by reading the JSON file, setting up states and transitions,
     * and defining the initial and final states.
     */
    init {
        val jsonString = File(filePath).readText()
        automatonData = Json.decodeFromString(jsonString)

        // add error state
        automatonData.states.add("error")

        // states
        for (state in automatonData.states) {
            require(!states.containsKey(state)) {"$state already exists"}
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
        check(states.containsKey(automatonData.initialState)) { "Initial state ${automatonData.initialState} is not defined" }
        initialState = states[automatonData.initialState]!!

        // final state
        for (s in automatonData.finalStates) {
            check(states.containsKey(s)) { "$s is not defined as a state" }
            finalStates.add(states[s]!!)
        }
        finalStates.add(states["error"]!!)
    }


    /**
     * Checks if the given word is accepted by the automaton.
     *
     * @param word The word to check.
     * @return True if the word is accepted, false otherwise.
     */
    override fun checkWord(word: String): Boolean {
        println("vérification de '$word'")

        var currentState = initialState
        var currentCharIndex = 0

        while (!finalStates.contains(currentState) && states.containsValue(currentState) && currentCharIndex < word.length) {
            println("${currentState.name} -> ${currentState.getNextState(word[currentCharIndex]).name} \t ${word[currentCharIndex]}")
            currentState = currentState.getNextState(word[currentCharIndex])
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

    /**
     * Generates a DOT file representation of the automaton.
     *
     * @param filePath The path where the DOT file will be saved.
     */
    override fun generateDotFile(filePath: String) {
        val dotFilePath = filePath.replaceAfterLast(".", "dot")
        automatonData.toDotFile(dotFilePath)
    }

    /**
     * Returns a string representation of the automaton.
     *
     * @return A string describing the automaton.
     */
    override fun toString(): String {
        return "automaton : ${automatonData.name} : ${automatonData.description}"
    }
}