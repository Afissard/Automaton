package automaton

enum class AutomatonType(val descrition:String) {
    SMILEY("Automaton for smileys"),
    SMILEY_JSON("Automaton for smileys (from json file)"),
    ABC("A basic automaton to produce 'abc'"),
    HOUR("Automaton for time in JJ:HH:MM format (not implemented)"),
    DATE("Automaton for date in DD/MM/YYYY format (not implemented)"),
    CUSTOM("Use your own automaton (not implemented)")
}