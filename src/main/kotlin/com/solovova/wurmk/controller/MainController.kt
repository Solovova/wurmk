package com.solovova.wurmk.controller

import com.solovova.wurmk.controller.data.DataTableLog
import com.solovova.wurmk.engine.EngineMain
import com.solovova.wurmk.engine.config.EngineConfig
import com.solovova.wurmk.keyhook.KeyHookListener
import javafx.collections.FXCollections
import javafx.collections.ObservableList
import javafx.fxml.FXML
import javafx.scene.control.ChoiceBox
import javafx.scene.control.TableColumn
import javafx.scene.control.TableView
import org.jnativehook.GlobalScreen
import org.jnativehook.NativeHookException


class MainController {
    private val logData: ObservableList<DataTableLog> = FXCollections.observableArrayList()
    private val cbCharData: ObservableList<String> = FXCollections.observableArrayList()
    private val cbTaskData: ObservableList<String> = FXCollections.observableArrayList()
    private var engineMain: EngineMain? = null

    @FXML
    lateinit var cbChar: ChoiceBox<String>

    @FXML
    lateinit var cbTask: ChoiceBox<String>

    @FXML
    lateinit var logTable: TableView<DataTableLog>

    @FXML
    lateinit var logColumn: TableColumn<DataTableLog, String>

    @FXML
    private fun buttonRunThread() {
    }

    @FXML
    private fun initialize() {
        logColumn.setCellValueFactory { cellData -> cellData.value.logProperty() }
        logTable.items = logData

        cbChar.items = cbCharData
        cbTask.items = cbTaskData



        cbChar.selectionModel.selectedIndexProperty().addListener { _, _, _ -> this.cbCharChange() }

        engineMain = EngineMain(this)
        cbCharChange()//ToDo переделать чтобы самому вытягивать данные для заполнения
    }

    private fun cbCharChange() {
        engineMain?.eventMCCharChange(cbChar.selectionModel.selectedIndex)
    }

    fun addLog(dataTableLog: DataTableLog) {
        logData.add(0, dataTableLog)
    }

    fun clearLog() {
        logData.clear()
    }

    fun stop() {
        engineMain?.stop()
    }

    fun update(config: EngineConfig) {
        config.cnfChars.forEach { cbCharData.add(it) }
        cbChar.selectionModel.select(config.cnfActiveChar)

        config.cnfTasks.forEach { cbTaskData.add(it) }
        cbTask.selectionModel.select(config.cnfActiveTask)
    }
}
