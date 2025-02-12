package automaton

class State(val name:String) {
    private val transitions:HashMap<Char, State> = hashMapOf()

    fun addTransition(toState:State, letter:Char){
        require(!transitions.containsKey(letter)) {
            "'$letter' already have a transition from $name"
        }
        transitions[letter] = toState
    }

    fun getNextState(letter: Char):State{
        if (!transitions.containsKey(letter)){
            // throw AutomatonExeption("$letter have no transition in this state")
            return transitions[' ']!!
        }
        return transitions[letter]!!
    }
}