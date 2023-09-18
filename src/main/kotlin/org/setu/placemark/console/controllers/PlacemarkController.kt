package org.setu.placemark.console.controllers

import mu.KotlinLogging
import org.setu.placemark.console.models.PlacemarkMemStore
import org.setu.placemark.console.models.PlacemarkModel
import org.setu.placemark.console.views.PlacemarkView

class PlacemarkController {

    val placemarks = PlacemarkMemStore()
    val placemarkView = PlacemarkView()
    val logger = KotlinLogging.logger {}

    init {
        logger.info { "Launching Placemark Console App" }
        println("Placemark Kotlin App Version 3.0")
    }

    fun menu(): Int {
        return placemarkView.menu()
    }

    fun add() {
        val addPlacemark = PlacemarkModel()
        if (placemarkView.addPlacemarkData(addPlacemark)) {
            placemarks.create(addPlacemark)
        } else {
            logger.info("Placemark Not Added")
        }
    }

    fun list() {
        placemarkView.listPlacemarks(placemarks)
    }

    fun update() {
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
}