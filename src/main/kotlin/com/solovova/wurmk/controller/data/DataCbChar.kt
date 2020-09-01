package com.solovova.wurmk.controller.data

import javafx.beans.property.SimpleStringProperty
import javafx.beans.property.StringProperty

class DataCbChar(name: String, value: String) {
    private val name: SimpleStringProperty = SimpleStringProperty(name)
    private val value: SimpleStringProperty = SimpleStringProperty(value)

    fun nameProperty(): StringProperty {
        return name
    }
}