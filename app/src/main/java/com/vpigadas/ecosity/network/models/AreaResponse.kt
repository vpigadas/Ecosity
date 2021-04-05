package com.vpigadas.ecosity.network.models

import com.google.android.gms.maps.model.LatLng
import com.google.gson.Gson

data class AreaResponse(
    val count: String,
    val areas: List<SpecAreaResponse>
)

data class SpecAreaResponse(
    val id: Int,
    val name: String,
    val typeId: String?,
    val classificationId: ClassificationAreaResponse,
    val usageId: UsageAreaResponse,
    val propertyId: PropertyAreaResponse,
    val history: String,
    val aream2: Double,
    val electricityprovision: Boolean,
    val lowlighting: Boolean,
    val waterprovision: Boolean,
    val localwaterprovision: String,
    val hasdrillings: Boolean,
    val drillings: List<Any>,
    val hasurbanequipments: Boolean,
    val urbanequipments: List<Any>,
    val hasretainingwalls: Boolean,
    val hasfenceconstruction: Boolean,
    val hasprecastcurbs: Boolean,
    val haspavingsidewalksisletssquares: Boolean,
    val hascementmortar: Boolean,
    val hasplayground: Boolean,
    val playgroundunits: List<Any>,
    private val locations: String,
    val municiapalityid: Int,
    val photos: List<Any>,
    val topviews: List<Any>
) {
    fun getLocations(): List<LatLng> {
        val list = mutableListOf<LatLng>()

        list.addAll(Gson().fromJson(locations, Array<LocationsResponse>::class.java)
            .map { LatLng(it.lat, it.lng) })

        return list
    }
}

data class LocationsResponse(
    val lat: Double,
    val lng: Double
)

data class ClassificationAreaResponse(
    val id: Int,
    val classification: String,
    val nameel: String,
    val nameen: String
)

data class UsageAreaResponse(
    val id: Int,
    val usage: String,
    val nameel: String,
    val nameen: String,
    val classificationid: ClassificationAreaResponse
)

data class PropertyAreaResponse(
    val id: Int,
    val property: String,
    val nameel: String,
    val nameen: String,
    val usageId: UsageAreaResponse
)