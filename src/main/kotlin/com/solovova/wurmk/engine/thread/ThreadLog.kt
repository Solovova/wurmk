package com.solovova.wurmk.engine.thread

import com.solovova.wurmk.engine.EngineMain
import java.io.BufferedReader
import java.io.FileInputStream
import java.io.InputStreamReader


class ThreadLog(private val fileName: String, private val engineMain: EngineMain) : Thread() {
    var stopIt: Boolean = false

    override fun run() {
        try {
            val fStream = FileInputStream(fileName)
            val br = BufferedReader(InputStreamReader(fStream))
            var strLine: String?

            var prevLine = ""
            while (br.readLine().also { strLine = it } != null) {
                prevLine = strLine ?: ""
            }

            engineMain.updateLog(prevLine)

            while (!this.stopIt) {
                if (br.readLine().also { strLine = it } != null) {
                    engineMain.updateLog(strLine ?: "")
                }
                sleep(50)
            }
            fStream.close()
        } catch (e: Exception) {
            System.err.println("Error: " + e.message)
        }
    }
}