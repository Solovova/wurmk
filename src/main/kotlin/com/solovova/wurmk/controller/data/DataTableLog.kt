package com.solovova.wurmk.controller.data

import javafx.beans.property.SimpleStringProperty
import javafx.beans.property.StringProperty

class DataTableLog internal constructor(log: String) {
    private val log: SimpleStringProperty = SimpleStringProperty(log)

    fun logProperty(): StringProperty {
        return log
    }
}