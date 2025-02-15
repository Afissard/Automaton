import automaton.Automaton
import java.io.File

/**
 * Main application class for running the automaton-based application.
 */
class Application (
    private val resourcePath:String
) {
    init {
        require(File(resourcePath).isDirectory) { "Resource path must be a directory" }
    }

    /**
     * Runs the application, displaying a menu and processing user input.
     */
    fun run() {
        println("---------- Nantes Université, BUT2 Informatique, 2025 ------------\n------- TP - Modélisation et analyse à l’aide des automates -------")
        println("------- Menu de l’application (développée par Sacha Chauvel) -------")

        val resourceDir = File(resourcePath)
        val jsonFiles = resourceDir.listFiles { _, name -> name.endsWith(".json") }?.map { it.nameWithoutExtension } ?: emptyList()

        while (true) {
            for ((index, fileName) in jsonFiles.withIndex()) {
                println("${index + 1} \t $fileName")
            }
            println("Votre choix (1-${jsonFiles.size}) ?")

            val autoTypeInput = getIntInput(1, jsonFiles.size)
            val selectedFile = jsonFiles[autoTypeInput - 1]
            val automaton = Automaton("$resourcePath$selectedFile.json")
            println("---\n$automaton")

            println("Choisissez une option:")
            println("1. Vérifier un mot")
            println("2. Générer un mot")
            println("3. Créer un fichier .dot")

            when (getIntInput(1, 3)) {
                1 -> {
                    println("Vous saisirez ensuite la chaîne à analyser, Merci")
                    var wordInput = readlnOrNull()
                    if (wordInput == null) {
                        wordInput = ""
                    }

                    val res = automaton.checkWord(wordInput)
                    println("Le mot '$wordInput' est ${if (res) "accepté" else "refusé"}")
                }
                2 -> {
                    println("mot générer : ${automaton.generateRandomWord()}")
                }
                3 -> {
                    val filePath = "./$selectedFile.dot"
                    automaton.generateDotFile(filePath)
                    println("Fichier .dot créé à l'emplacement: $filePath")
                }
                4 -> return
            }

            println("Voulez-vous choisir un autre automate? (oui/non)")
            if (readlnOrNull()?.lowercase() != "oui") {
                break
            }
        }

        println("------------------------------------------------------------------")
    }

    /**
     * Reads an integer input from the user.
     *
     * @param min The minimum value allowed.
     * @param max The maximum value allowed.
     * @return The integer input from the user.
     */
    private fun getIntInput(min:Int, max:Int): Int {
        var input: Int? = null
        while (input == null || input < min || input > max) {
            try {
                input = readlnOrNull()?.toInt()
                if (input == null || input < min || input > max) {
                    println("Invalid input. Please enter a valid integer between $min and $max.")
                }
            } catch (e: NumberFormatException) {
                println("Invalid input. Please enter a valid integer between $min and $max.")
            }
        }
        return input
    }

    companion object {
        /**
         * Main function to start the application.
         */
        @JvmStatic
        fun main(args: Array<String>) {
            val resourcePath = if (args.isNotEmpty()) {
                args[0]
            } else {
                println("Resource path not provided. Using default path: ./src/main/resources/")
                //"./src/main/resources/"
                "./"
            }

            Application(resourcePath).run()
        }
    }
}
