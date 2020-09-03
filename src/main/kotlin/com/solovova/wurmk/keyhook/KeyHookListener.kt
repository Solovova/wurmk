package com.solovova.wurmk.keyhook

import com.solovova.wurmk.engine.EngineMain
import org.jnativehook.GlobalScreen
import org.jnativehook.NativeHookException
import org.jnativehook.keyboard.NativeKeyEvent
import org.jnativehook.keyboard.NativeKeyListener
import java.awt.AWTException
import java.util.logging.Level
import java.util.logging.LogManager
import java.util.logging.Logger


class KeyHookListener internal constructor(private val engineMain: EngineMain) : NativeKeyListener {
    private val pressedKeys: MutableList<Int> = mutableListOf()

    private fun isPressed(key: Int): Boolean {
        return pressedKeys.indexOf(key) != -1
    }

    override fun nativeKeyPressed(e: NativeKeyEvent) {

        if (!isPressed(e.keyCode)) {
            pressedKeys.add(e.keyCode)
        }
        //println(pressedKeys)

        //println("Key Pressed: " + NativeKeyEvent.getKeyText(e.keyCode))
        if (e.keyCode == NativeKeyEvent.VC_ESCAPE) {
            try {
                GlobalScreen.unregisterNativeHook()
            } catch (ex: NativeHookException) {
                ex.printStackTrace()
            }
        }

        if (isPressed(NativeKeyEvent.VC_SHIFT) && isPressed(NativeKeyEvent.VC_F2)) {
            try {
                engineMain.btnStartMonitor()
            } catch (ex: AWTException) {
                ex.printStackTrace()
            }
        }
    }

    override fun nativeKeyReleased(e: NativeKeyEvent) {
        if (isPressed(e.keyCode)) {
            pressedKeys.remove(e.keyCode)
        }
        //println("Key Released: " + NativeKeyEvent.getKeyText(e.keyCode))
    }

    override fun nativeKeyTyped(e: NativeKeyEvent) {
        //println("Key Typed: " + NativeKeyEvent.getKeyText(e.keyCode))
    }

    fun hookRegister() {
        try {
            GlobalScreen.registerNativeHook()
        } catch (ex: NativeHookException) {
            System.err.println("There was a problem registering the native hook.")
            System.err.println(ex.message)
        }

        GlobalScreen.addNativeKeyListener(KeyHookListener(engineMain))

        LogManager.getLogManager().reset()

        val logger: Logger = Logger.getLogger(GlobalScreen::class.java.getPackage().name)
        logger.level = Level.OFF
    }

    fun hookUnRegister() {
        GlobalScreen.unregisterNativeHook()
    }


}