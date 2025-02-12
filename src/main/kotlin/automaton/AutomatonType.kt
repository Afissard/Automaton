package automaton

enum class AutomatonType(val descrition:String) {
    SMILEY("Automaton for smileys"),
    SMILEY_JSON("Automaton for smileys (from json file)"),
    ABC("A basic automaton to produce 'abc'"),
    HOUR("Automaton for time in JJ:HH:MM format"),
    DATE("Automaton for date in JJ/MM/AAAA format")
}