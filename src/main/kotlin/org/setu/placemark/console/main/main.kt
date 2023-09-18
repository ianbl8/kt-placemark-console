package org.setu.placemark.console.main

import mu.KotlinLogging
import org.setu.placemark.console.controllers.PlacemarkController
import org.setu.placemark.console.models.PlacemarkMemStore
import org.setu.placemark.console.models.PlacemarkModel
import org.setu.placemark.console.views.PlacemarkView

private val logger = KotlinLogging.logger {}

val placemarks = PlacemarkMemStore()
val placemarkView = PlacemarkView()
val placemarkController = PlacemarkController()

fun main(args: Array<String>){
    logger.info { "Launching Placemark Console App" }
    println("Placemark Kotlin App Version 3.0")
    var input: Int
    do {
        input = placemarkView.menu()
        when(input) {
            1 -> placemarkController.add()
            2 -> placemarkController.update()
            3 -> placemarkController.list()
            4 -> placemarkController.searchPlacemarks()
            -99 -> placemarkController.dummyData()
            -1 -> println("Exiting Placemark App")
            else -> println("Invalid Option")
        }
        println()
    } while (input != -1)
    logger.info { "Shutting down Placemark Console App" }
}
