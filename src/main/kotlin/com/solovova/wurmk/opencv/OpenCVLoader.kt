package com.solovova.wurmk.opencv

import org.opencv.core.Core
import java.io.File
import java.io.InputStream

class OpenCVLoader {
    fun loadLibraries() {
        try {
            val `in`: InputStream? = null
            val fileOut: File? = null
            val osName = System.getProperty("os.name")
            var opencvpath = System.getProperty("user.dir")
            println(opencvpath)
            if (osName.startsWith("Windows")) {
                val bitness = System.getProperty("sun.arch.data.model").toInt()
                opencvpath = if (bitness == 32) {
                    "$opencvpath\\opencv\\x86\\"
                } else if (bitness == 64) {
                    "$opencvpath\\opencv\\x64\\"
                } else {
                    "$opencvpath\\opencv\\x86\\"
                }
            } else if (osName == "Mac OS X") {
                opencvpath += "Your path to .dylib"
            }
            println(opencvpath)
            println(opencvpath + Core.NATIVE_LIBRARY_NAME + ".dll")
            System.load(opencvpath + Core.NATIVE_LIBRARY_NAME + ".dll")
        } catch (e: Exception) {
            throw RuntimeException("Failed to load opencv native library", e)
        }
    }
}