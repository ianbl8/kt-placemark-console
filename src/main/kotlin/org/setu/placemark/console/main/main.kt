package org.setu.placemark.console.main

import mu.KotlinLogging
import org.setu.placemark.console.models.PlacemarkMemStore
import org.setu.placemark.console.models.PlacemarkModel
import org.setu.placemark.console.views.PlacemarkView

private val logger = KotlinLogging.logger {}

val placemarks = PlacemarkMemStore()
val placemarkView = PlacemarkView()

fun main(args: Array<String>){
    logger.info { "Launching Placemark Console App" }
    println("Placemark Kotlin App Version 3.0")
    var input: Int
    do {
        input = placemarkView.menu()
        when(input) {
            1 -> addPlacemark()
            2 -> updatePlacemark()
            3 -> placemarkView.listPlacemarks(placemarks)
            4 -> searchPlacemarks()
            -99 -> dummyData()
            -1 -> println("Exiting Placemark App")
            else -> println("Invalid Option")
        }
        println()
    } while (input != -1)
    logger.info { "Shutting down Placemark Console App" }
}

fun addPlacemark() {
    val addPlacemark = PlacemarkModel()
    if (placemarkView.addPlacemarkData(addPlacemark)) {
        placemarks.create(addPlacemark)
    } else {
        logger.info("Placemark Not Added")
    }
}

fun updatePlacemark() {
    placemarkView.listPlacemarks(placemarks)
    var searchId = placemarkView.getId()
    val updatePlacemark = search(searchId)
    if (updatePlacemark != null) {
        if (placemarkView.updatePlacemarkData(updatePlacemark)) {
            placemarks.update(updatePlacemark)
            placemarkView.showPlacemark(updatePlacemark)
            logger.info("Placemark Updated: $updatePlacemark")
        }
        else
            logger.info("Placemark Not Updated")
    } else {
        logger.info("Placemark Not Updated")
    }
}

fun searchPlacemarks() {
    val foundPlacemark = search(placemarkView.getId())!!
    placemarkView.showPlacemark(foundPlacemark)
}

fun search(id: Long) : PlacemarkModel? {
    var foundPlacemark = placemarks.findOne(id)
    return foundPlacemark
}

fun dummyData() {
    placemarks.create(PlacemarkModel(title = "Ballyfermot", description = "Home of \"Lam's\" and \"New Lam's\""))
    placemarks.create(PlacemarkModel(title = "Chessington", description = "It's a World of Adventures!"))
    placemarks.create(PlacemarkModel(title = "Brussels", description = "Bruxelles ma belle !"))
    placemarks.create(PlacemarkModel(title = "Berlin", description = "Das war die schönste Zeit!"))
}