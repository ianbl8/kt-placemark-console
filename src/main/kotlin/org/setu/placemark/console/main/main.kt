package org.setu.placemark.console.main

import mu.KotlinLogging
import org.setu.placemark.console.models.PlacemarkModel

private val logger = KotlinLogging.logger {}

val placemarks = ArrayList<PlacemarkModel>()

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
            4 -> searchPlacemarks()
            -99 -> dummyData()
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
    println(" 4. Search Placemarks")
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
    var addPlacemark = PlacemarkModel()
    print("Enter a title: ")
    addPlacemark.title = readLine()!!
    print("Enter a description: ")
    addPlacemark.description = readLine()!!
    if (addPlacemark.title.isNotEmpty() && addPlacemark.description.isNotEmpty()) {
        addPlacemark.id = placemarks.size.toLong()
        placemarks.add(addPlacemark.copy())
        logger.info("Placemark Added: $addPlacemark")
    } else {
        logger.info("Placemark Not Added")
    }
}

fun updatePlacemark() {
    println("Update Placemark")
    println()
    listAllPlacemarks()
    var searchId = getId()
    val updatePlacemark = search(searchId)
    if (updatePlacemark != null) {
        print("Enter a new title: ")
        updatePlacemark.title = readLine()!!
        print("Enter a new description: ")
        updatePlacemark.description = readLine()!!
        logger.info("Placemark Updated: $updatePlacemark")
    } else {
        logger.info("Placemark Not Updated")
    }
}

fun listAllPlacemarks() {
    println("List All Placemarks")
    println()
    placemarks.forEach { logger.info("${it}") }
    println()
}

fun searchPlacemarks() {
    var searchId = getId()
    val foundPlacemark = search(searchId)
    if (foundPlacemark != null) {
        logger.info("Found: $foundPlacemark")
    } else {
        logger.info("Placemark Not Found")
    }
}

fun getId() : Long {
    var strId : String?
    var searchId : Long
    print("Enter ID to Search/Update: ")
    strId = readLine()!!
    searchId = if (strId.toLongOrNull() != null && !strId.isEmpty())
        strId.toLong()
    else
        -9
    return searchId
}

fun search(id: Long) : PlacemarkModel? {
    var foundPlacemark: PlacemarkModel? = placemarks.find { p -> p.id == id }
    return foundPlacemark
}

fun dummyData() {
    placemarks.add(PlacemarkModel(0, "Ballyfermot", "Home of \"Lam's\" and \"New Lam's\""))
    placemarks.add(PlacemarkModel(1, "Chessington", "It's a World of Adventures!"))
    placemarks.add(PlacemarkModel(2, "Brussels", "Bruxelles ma belle !"))
    placemarks.add(PlacemarkModel(3, "Berlin", "Das war die schönste Zeit!"))
}