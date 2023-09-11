package org.setu.placemark.console.main

import mu.KotlinLogging

private val logger = KotlinLogging.logger {}

// Properties used for Placemark
var title: String = ""
var description: String = ""

fun main(args: Array<String>){
    logger.info { "Launching Placemark Console App" }
    println("Placemark Kotlin App Version 2.0")

    var input: Int

    do {
        input = menu()
        when(input) {
            1 -> addPlacemark()
            2 -> updatePlacemark()
            3 -> listAllPlacemarks()
            -1 -> println("Exiting Placemark App")
            else -> println("Invalid Option")
        }
        println()
    } while (input != -1)
    logger.info { "Shutting down Placemark Console App" }
}

fun menu(): Int {

    var option: Int
    var input: String? = null

    println("Main Menu")
    println(" 1. Add Placemark")
    println(" 2. Update Placemark")
    println(" 3. List All Placemarks")
    println("-1. Exit")
    println()
    print("Enter an integer: ")
    input = readLine()!!
    option = if (input.toIntOrNull() != null && !input.isEmpty())
        input.toInt()
    else
        -9

    return option
}

fun addPlacemark() {
    println("Add Placemark")
    println()
    print("Enter a title: ")
    title = readLine()!!
    print("Enter a description: ")
    description = readLine()!!
    println("You entered $title for title, and $description for description")
}

fun updatePlacemark() {
    println("You chose Update Placemark")
}

fun listAllPlacemarks() {
    println("You chose List All Placemarks")
}
