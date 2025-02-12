package automaton

import kotlinx.serialization.Serializable
import java.io.File


@Serializable
data class AutomatonData(
    val alphabet: ArrayList<Char>,
    val states: ArrayList<String>,
    var initialState: String,
    val finalStates: ArrayList<String>,
    val transitions: ArrayList<TransitionData>
)

@Serializable
data class TransitionData(val from: String,val to: String, val label: ArrayList<Char>)


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