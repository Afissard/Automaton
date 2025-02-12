package automaton

import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import java.io.File

/**
 * Custom automaton class that initializes from a JSON file.
 *
 * @param filePath The path to the JSON file containing the automaton data.
 */
class AutomatonCUSTOM(filePath: String) : Automaton() {
    init {
        val jsonString = File(filePath).readText()
        automatonData = Json.decodeFromString(jsonString)
        initFromAutomatonData()

        val dotFilePath = filePath.replaceAfterLast(".", "dot")
        automatonData.toDotFile(dotFilePath)
    }
}
