package automaton

import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import java.io.File

class AutomatonCUSTOM(filePath: String) : Automaton() {
    init {
        val jsonString = File(filePath).readText()
        automatonData = Json.decodeFromString(jsonString)
        initFromAutomatonData()

        val dotFilePath = filePath.replaceAfterLast(".", "dot")
        automatonData.toDotFile(dotFilePath)
    }
}
