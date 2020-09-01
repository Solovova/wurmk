package com.solovova.wurmk.engine.config

import com.solovova.wurmk.engine.config.data.DataChar
import org.ini4j.Wini
import java.io.File


class EngineConfig {
    var cnfChars: List<String> = listOf()
    var cnfActiveChar: Int = 3
    private val cnfCharsData: MutableMap<String, DataChar> = mutableMapOf()

    var cnfTasks: List<String> = listOf()
    var cnfActiveTask: Int = 0

    private fun loadCharsData(ini: Wini) {
        cnfChars.forEach {
            val dataChar = DataChar()
            dataChar.nameLog = ini.get(it, "nameLog")
            cnfCharsData[it] = dataChar
        }
    }

    fun getActiveDataChar():DataChar {
        return cnfCharsData[cnfChars[cnfActiveChar]] ?: DataChar()
    }

    fun load() {
        val ini = Wini(File("config.ini"))
        val characters: String = ini.get("Main", "characters")
        cnfChars = characters.trim().split(",").map { it.trim() }

        loadCharsData(ini)

        val tasks: String = ini.get("Main", "operations")
        cnfTasks = tasks.trim().split(",").map { it.trim() }
    }

    fun save() {
//        try {
//            val ini = Wini(File("config.ini"))
//            ini.put("block_name", "property_name", "value")
//            ini.put("block_name", "property_name_2", 45.6)
//            ini.store()
//
//            // To catch basically any error related to writing to the file
//            // (The system cannot find the file specified)
//        } catch (e: Exception) {
//            System.err.println(e.message)
//        }
    }
}