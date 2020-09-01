package com.solovova.wurmk

import com.solovova.wurmk.controller.MainController
import javafx.application.Application
import javafx.fxml.FXMLLoader
import javafx.scene.Scene
import javafx.scene.layout.Pane
import javafx.stage.Stage

class MainGUI : Application() {
    private val layout = "/main.fxml"
    private var mainController: MainController? = null

    override fun start(primaryStage: Stage?) {
        val fxmlLoader = FXMLLoader()
        val root: Pane = fxmlLoader.load(javaClass.getResource(layout).openStream())

        mainController = fxmlLoader.getController() as MainController

        primaryStage?.scene = Scene(root)
        primaryStage?.show()
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