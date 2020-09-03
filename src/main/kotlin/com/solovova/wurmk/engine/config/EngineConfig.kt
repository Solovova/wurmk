package com.solovova.wurmk.engine.config

import com.solovova.wurmk.engine.config.data.DataChar
import com.solovova.wurmk.engine.config.data.DataTask
import org.ini4j.Wini
import java.io.File

class EngineConfig {
    var cnfChars: List<String> = listOf()
    var cnfActiveChar: Int = 3
    private val cnfCharsData: MutableMap<String, DataChar> = mutableMapOf()

    var cnfTasks: List<String> = listOf()
    var cnfActiveTask: Int = 0
    private val cnfTaskData: MutableMap<String, DataTask> = mutableMapOf()

    private fun iniGetDefString(ini: Wini, sectionName: String, optionName: String, defResult: String): String {
        return ini.get(sectionName, optionName) ?: defResult
    }

    private fun iniGetDefInt(ini: Wini, sectionName: String, optionName: String, defResult: Int): Int {
        val result: String? = ini.get(sectionName, optionName)
        if (result != null) {
            return result.toInt()
        }
        return defResult
    }

    fun getActiveDataChar(): DataChar {
        return cnfCharsData[cnfChars[cnfActiveChar]] ?: DataChar()
    }

    fun load() {
        val ini = Wini(File("config.ini"))
        val characters: String = iniGetDefString(ini, "Main", "characters", "Empty")
        cnfChars = characters.trim().split(",").map { it.trim() }

        cnfChars.forEach {
            cnfCharsData[it] = DataChar(nameLog = iniGetDefString(ini, it, "nameLog", ""))
        }

        val tasks: String = iniGetDefString(ini, "Main", "operations", "Empty")
        cnfTasks = tasks.trim().split(",").map { it.trim() }

        cnfTasks.forEach {
            cnfTaskData[it] = DataTask(strTask = iniGetDefString(ini, it, "ToDo", ""),
                    strPoints = iniGetDefString(ini, it, "Point", ""))
        }
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