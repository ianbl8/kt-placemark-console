package org.setu.placemark.console.views

import org.setu.placemark.console.models.PlacemarkJSONStore
import org.setu.placemark.console.models.PlacemarkMemStore
import org.setu.placemark.console.models.PlacemarkModel

class PlacemarkView {

    fun menu(): Int {
        var option: Int
        var input: String?
        println("\nMain Menu")
        println("=========")
        println(" 1. Add Placemark")
        println(" 2. Update Placemark")
        println(" 3. List All Placemarks")
        println(" 4. Search Placemarks")
        println("-1. Exit\n")
        print("Enter option: ")
        input = readLine()!!
        option = if (input.toIntOrNull() != null && input.isNotEmpty())
            input.toInt()
        else
            -9
        return option
    }

    fun addPlacemarkData(placemark: PlacemarkModel) : Boolean {
        println()
        print("Enter a title: ")
        placemark.title = readLine()!!
        print("Enter a description: ")
        placemark.description = readLine()!!
        return placemark.title.isNotEmpty() && placemark.description.isNotEmpty()
    }

    fun updatePlacemarkData(placemark: PlacemarkModel) : Boolean {
        val newTitle: String?
        val newDescription: String?
        var updated: Boolean = false
        if (placemark != null) {
            print("Enter a new title for " + placemark.title + ": ")
            newTitle = readLine()!!
            print("Enter a new description for " + placemark.description + ": ")
            newDescription = readLine()!!
            if (!newTitle.isNullOrEmpty()) {
                placemark.title = newTitle
                updated = true
            }
            if (!newDescription.isNullOrEmpty()) {
                placemark.description = newDescription
                updated = true
            }
        }
        return updated
    }

    fun listPlacemarks(placemarks: PlacemarkJSONStore) {
        println()
        println("List All Placemarks")
        placemarks.logAll()
        println()
    }

    fun showPlacemark(placemark: PlacemarkModel) {
        println()
        if (placemark != null)
            println("Placemark details: $placemark")
        else
            println("Placemark not found")
    }

    fun getId(): Long {
        var strId: String?
        var searchId: Long
        print("Enter ID to search/update: ")
        strId = readLine()!!
        searchId = if (strId.toLongOrNull() != null && strId.isNotEmpty())
            strId.toLong()
        else
            -9
        return searchId
    }
}