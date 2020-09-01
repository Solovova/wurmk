package com.solovova.wurmk.engine.script

class ScriptFunctions {
    private var data: List<String> = listOf()
    fun call(fName: String, data: List<String>):Boolean {
        this.data = data
        val f = this.javaClass.getMethod(fName)
        return f.invoke(this) as Boolean
    }

    fun sfAdd():Boolean{
        println("Add $data")
        return false
    }

    fun sfSub():Boolean{
        println("Sub $data")
        return true
    }
}