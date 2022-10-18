package com.whichonespink.spacex.data.mapper

import com.whichonespink.spacex.data.remote.dto.MissionDto
import com.whichonespink.spacex.domain.model.Mission

/**
 * Mapper functions to transform data layer classes into domain layer classes
 */

fun MissionDto.toMission() : Mission{
    return Mission(
        name = name,
        flightNumber = flight
    )
}