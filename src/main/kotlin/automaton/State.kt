package automaton

class State(val name:String) {
    private val transitions:HashMap<Char, State> = hashMapOf()

    fun addTransition(state:State, letter:Char){
        if (transitions.containsKey(letter)){
            throw AutomatonExeption("$letter already have a transition from this state")
        }
        transitions[letter] = state
    }

    fun getNextState(letter: Char):State{
        if (!transitions.containsKey(letter)){
            throw AutomatonExeption("$letter have no transition in this state")
        }
        return transitions[letter]!!
    }
}