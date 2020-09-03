package com.solovova.wurmk

import com.solovova.wurmk.controller.MainController
import com.solovova.wurmk.opencv.OpenCVLoader
import javafx.application.Application
import javafx.fxml.FXMLLoader
import javafx.scene.Scene
import javafx.scene.layout.Pane
import javafx.scene.layout.StackPane
import javafx.stage.Stage
import org.opencv.core.Core

import java.io.File
import java.io.InputStream


class MainGUI : Application() {



    private val layout = "/main.fxml"
    private var mainController: MainController? = null

    override fun start(primaryStage: Stage?) {
        OpenCVLoader().loadLibraries()

        val fxmlLoader = FXMLLoader()
        val root: Pane = fxmlLoader.load(javaClass.getResource(layout).openStream())

        mainController = fxmlLoader.getController() as MainController
        mainController?.mainGUI = this

        primaryStage?.scene = Scene(root)
        primaryStage?.show()
    }

    fun showSt() {

    }

    override fun stop() {
        mainController?.stop()
        super.stop()
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            launch(MainGUI::class.java)
        }
    }

}