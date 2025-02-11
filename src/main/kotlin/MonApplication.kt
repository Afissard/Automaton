import automaton.Automaton
import automaton.AutomatonExeption
import automaton.AutomatonType

class MonApplication {
    fun run(){
        println("---------- Nantes Université, BUT2 Informatique, 2025 ------------\n------- TP - Modélisation et analyse à l’aide des automates -------")
        println("------- Menu de l’application (développée par Sacha Chauvel) -------")
        for (i in AutomatonType.entries){
            println("${i.ordinal + 1} \t ${i.name} \t ${i.descrition}")
        }
        println("Votre choix (1-${AutomatonType.entries.size}) ?")

        val autoTypeInput = getIntInput()
        if (autoTypeInput < 1 || autoTypeInput > AutomatonType.entries.size) {
            println("Invalid input. Please enter a valid integer.")
            return
        }
        val automaton = Automaton.createAutomaton(AutomatonType.entries[autoTypeInput - 1])

        println("Vous saisirez ensuite la chaîne `a analyser, Merci")
        var wordInput = readlnOrNull()
        if (wordInput == null) {
            wordInput = ""
        }

        try {
            automaton.checkWord(wordInput)
        } catch (e: AutomatonExeption) {
            println(e.message)
        }

        println("------------------------------------------------------------------")
    }


    private fun getIntInput(): Int {
        var input: Int? = null
        while (input == null) {
            try {
                input = readlnOrNull()?.toInt()
            } catch (e: NumberFormatException) {
                println("Invalid input. Please enter a valid integer.")
            }
        }
        return input
    }

}

fun main() {
    MonApplication().run()
}