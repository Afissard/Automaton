package automaton

import kotlinx.serialization.Serializable
import java.io.File

/**
 * Data class representing the automaton data.
 *
 * @property name The name of the automaton.
 * @property description The description of the automaton.
 * @property alphabet The alphabet of the automaton.
 * @property states The states of the automaton.
 * @property initialState The initial state of the automaton.
 * @property finalStates The final states of the automaton.
 * @property transitions The transitions of the automaton.
 */
@Serializable
data class AutomatonData(
    val name: String,
    val description: String,
    val alphabet: ArrayList<Char>,
    val states: ArrayList<String>,
    var initialState: String,
    val finalStates: ArrayList<String>,
    val transitions: ArrayList<TransitionData>
)

/**
 * Data class representing a transition in the automaton.
 *
 * @property from The starting state of the transition.
 * @property to The ending state of the transition.
 * @property label The labels for the transition.
 */
@Serializable
data class TransitionData(val from: String,val to: String, val label: ArrayList<Char>)

/**
 * Extension function to generate a DOT file from the automaton data.
 *
 * @param filePath The path where the DOT file will be saved.
 */
fun AutomatonData.toDotFile(filePath: String) {
    val dotContent = StringBuilder()
    dotContent.append("digraph Automaton {\n")
    dotContent.append("  rankdir=LR;\n")
    dotContent.append("  node [shape = doublecircle]; ${finalStates.joinToString(" ")};\n")
    dotContent.append("  node [shape = circle];\n")

    val transitionMap = mutableMapOf<Pair<String, String>, MutableList<Char>>()

    for (transition in transitions) {
        if (transition.to != "error") { // Ignore error state
            val key = Pair(transition.from, transition.to)
            if (key !in transitionMap) {
                transitionMap[key] = mutableListOf()
            }
            transitionMap[key]!!.addAll(transition.label)
        }
    }

    for ((key, labels) in transitionMap) {
        dotContent.append("  ${key.first} -> ${key.second} [label = \"${labels.joinToString(",")}\"];\n")
    }

    dotContent.append("  ${initialState} [shape = circle];\n")
    dotContent.append("  \"\" [shape = point];")
    dotContent.append("  \"\"-> ${initialState};\n")
    dotContent.append("}\n")

    File(filePath).writeText(dotContent.toString())
}