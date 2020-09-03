package com.solovova.wurmk.engine

import com.solovova.wurmk.controller.MainController
import com.solovova.wurmk.controller.data.DataTableLog
import com.solovova.wurmk.engine.config.EngineConfig
import com.solovova.wurmk.engine.thread.ThreadLog
import com.solovova.wurmk.keyhook.KeyHookListener
import javafx.application.Platform
import javafx.embed.swing.SwingFXUtils
import javafx.scene.Scene
import javafx.scene.image.Image
import javafx.scene.image.ImageView
import javafx.scene.layout.StackPane
import javafx.stage.Modality
import javafx.stage.Stage
import java.awt.Rectangle
import java.awt.Robot
import java.io.File


//MainController <-----> EngineMain ----> EngineConfig
//                                    --> ScriptEngine (thread)
//                                    --> ThreadLog

class EngineMain(private val mainController: MainController) {
    private var threadLog: ThreadLog? = null
    private val engineConfig: EngineConfig = EngineConfig()
    private val keyHookListener: KeyHookListener = KeyHookListener(this)

    init {
        engineConfig.load()
        mainController.update(engineConfig)
        keyHookListener.hookRegister()
    }

    fun updateLog(logStr: String) {
        mainController.addLog(DataTableLog(logStr))
    }

    fun stop() {
        threadLog?.stopIt = true
        keyHookListener.hookUnRegister()
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

    //Hooks
    fun btnStartMonitor(){
        println("Hook down")
        Platform.runLater {
            val robot = Robot()
            val bufferedImage = robot.createScreenCapture(Rectangle(0,0,1920,1080))

            val secondaryLayout = StackPane()
            val imageView = ImageView()
            imageView.image = SwingFXUtils.toFXImage(bufferedImage, null );
            secondaryLayout.children.add(imageView)

            val secondScene = Scene(secondaryLayout, 1920.0, 1080.0)

            // New window (Stage)

            // New window (Stage)
            val newWindow = Stage()
            newWindow.title = "Second Stage"
            newWindow.scene = secondScene
            newWindow.isFullScreen = true
            newWindow.isIconified = false
            newWindow.isResizable = false
            newWindow.initModality(Modality.APPLICATION_MODAL)

            // Set position of second window, related to primary window.

            // Set position of second window, related to primary window.
            newWindow.x = 0.0
            newWindow.y = 0.0

            newWindow.show()
        }
    }
}