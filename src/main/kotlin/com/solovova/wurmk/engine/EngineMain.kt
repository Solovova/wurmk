package com.solovova.wurmk.engine

import com.solovova.wurmk.controller.MainController
import com.solovova.wurmk.controller.data.DataTableLog
import com.solovova.wurmk.engine.script.ScriptFunctions
import com.solovova.wurmk.engine.thread.ThreadLog
import com.solovova.wurmk.engine.config.EngineConfig
import java.io.File

//MainController <-----> EngineMain ----> EngineConfig
//                                    --> ScriptEngine (thread)
//                                    --> ThreadLog

class EngineMain(private val mainController: MainController) {
    private var threadLog: ThreadLog? = null
    private val engineConfig: EngineConfig = EngineConfig()

    val scriptFunctions: ScriptFunctions = ScriptFunctions()

    init {
        engineConfig.load()
        mainController.update(engineConfig)
    }

    fun updateLog(logStr: String) {
        mainController.addLog(DataTableLog(logStr))
    }

    fun stop() {
        threadLog?.stopIt = true
    }

    fun eventMCCharChange(ind: Int) {
        this.engineConfig.cnfActiveChar = ind
        val nameLog = this.engineConfig.getActiveDataChar().nameLog
        println(nameLog)
        if (File(nameLog).exists()) {
            threadLog?.stopIt = true
            mainController.clearLog()

            val locThreadLog = ThreadLog(nameLog, this)
            locThreadLog.start()
            threadLog = locThreadLog
        }else{
            threadLog?.stopIt = true
            mainController.clearLog()
            mainController.addLog(DataTableLog("Not exists!!!"))
        }
    }
}