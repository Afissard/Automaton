package automaton

interface AutomatonInterface {
    fun initFromAutomatonData()

    fun checkWord(word : String) : Boolean
}