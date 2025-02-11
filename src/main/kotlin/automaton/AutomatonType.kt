package automaton

enum class AutomatonType(val descrition:String) {
    SMILEY("Automaton for smileys"),
    SMILEY_JSON("Automaton for smileys (from json file)"),
    ABC("A basic automaton to produce 'abc'"),
    DATE("Automaton for time in JJ:HH:MM format (not implemented)"),
    EMAIL("Automaton for email (not implemented)"),
    CUSTOM("Use your own automaton (not implemented)")
}