package automaton

import kotlinx.serialization.Serializable

@Serializable
data class AutomatonData(
    val alphabet: ArrayList<Char>,
    val states: ArrayList<String>,
    var initialState: String,
    val finalStates: ArrayList<String>,
    val transitions: ArrayList<TransitionData>
)

@Serializable
data class TransitionData(val from: String,val to: String, val letter: ArrayList<Char>)
