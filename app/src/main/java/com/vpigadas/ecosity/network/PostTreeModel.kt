package com.vpigadas.ecosity.network

data class PostTreeModel(
    var areaid: Int = -1,
    var typeid: Int = -1,
    var latitude: Double = 0.0,
    var longitude: Double = 0.0,
    var photo: String = "",
    var height: Int = -1,
    var angleid: Int = -1,
    var leaftype: Int = -1,
    var leafbranch: Int = -1,
    var diameter: Int = -1,
    var trunksurfaceid: Int = -1,
    var plantbasinid: Int = -1,
    var groundtypeid: Int = -1,
    var conditionid: Int = -1,
    var municipalityId: Int = -1
) {
    fun isValid(): Boolean {

        listOf<Int>(
            areaid,
            typeid,
            height,
            angleid,
            leaftype,
            leafbranch,
            diameter,
            trunksurfaceid,
            plantbasinid,
            groundtypeid,
            conditionid,
            municipalityId
        ).forEach {
            when (it < 0) {
                true -> return false
            }
        }

        listOf<Double>(latitude, longitude).forEach {
            when (it < 0.0) {
                true -> return false
            }
        }

        listOf<String>(photo).forEach {
            when (it.isEmpty()) {
                true -> return false
            }
        }

        return true
    }

    fun isValidAddress(): Boolean {
        listOf<Int>(
            areaid,
            typeid
        ).forEach {
            when (it < 0) {
                true -> return false
            }
        }

        listOf<Double>(latitude, longitude).forEach {
            when (it < 0.0) {
                true -> return false
            }
        }

        return true
    }

    fun isValidInfo(): Boolean {

        listOf<Int>(
            height,
            angleid,
            leaftype,
            groundtypeid,
            municipalityId
        ).forEach {
            when (it < 0) {
                true -> return false
            }
        }

        listOf<String>(photo).forEach {
            when (it.isEmpty()) {
                true -> return false
            }
        }

        return true
    }

    fun isValidDetails(): Boolean {

        listOf<Int>(
            leafbranch,
            diameter,
            trunksurfaceid,
            plantbasinid,
            conditionid
        ).forEach {
            when (it < 0) {
                true -> return false
            }
        }

        return true
    }
}