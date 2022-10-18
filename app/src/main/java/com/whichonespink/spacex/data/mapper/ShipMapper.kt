package com.whichonespink.spacex.data.mapper


import androidx.core.net.toUri
import com.whichonespink.spacex.data.remote.dto.ShipDto
import com.whichonespink.spacex.domain.model.Ship

/**
 * Mapper functions to transform data layer classes into domain layer classes
 */

fun ShipDto.toShip(): Ship{
    val imageUri = imageUrl.let {
        it?.toUri()?.buildUpon()?.scheme("https")?.build()
    }

    val missions = missions.map {
        it.toMission()
    }

    return Ship(
        name = shipName,
        type = shipType,
        homePort = homePort,
        imageUrl = imageUri,
        missions = missions,
    )
}