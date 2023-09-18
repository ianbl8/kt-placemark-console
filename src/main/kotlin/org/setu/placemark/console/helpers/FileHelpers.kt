package org.setu.placemark.console.helpers

import mu.KotlinLogging
import java.io.*

val logger = KotlinLogging.logger {}

fun write(filename: String, data: String) {
    val file = File(filename)
    try {
        val outputStreamWriter = OutputStreamWriter(FileOutputStream(file))
        outputStreamWriter.write(data)
        outputStreamWriter.close()
    } catch (e: Exception) {
        logger.error { "Cannot write file: "  + e.toString() }
    }
}

fun read(filename: String) : String {
    val file = File(filename)
    var str = ""
    try {
        val inputStreamReader = InputStreamReader(FileInputStream(file))
        if (inputStreamReader != null) {
            val bufferedReader = BufferedReader(inputStreamReader)
            val partialStr = StringBuilder()
            var done = false
            while (!done) {
                val line = bufferedReader.readLine()
                done = (line == null)
                if (line != null) partialStr.append(line)
            }
            inputStreamReader.close()
            str = partialStr.toString()
        }
    } catch (e: FileNotFoundException) {
        logger.error { "Cannot find file: " + e.toString() }
    } catch (e: IOException) {
        logger.error { "Cannot read file: " + e.toString() }
    }
    return str
}

fun exists(filename: String): Boolean {
    val file = File(filename)
    return file.exists()
}