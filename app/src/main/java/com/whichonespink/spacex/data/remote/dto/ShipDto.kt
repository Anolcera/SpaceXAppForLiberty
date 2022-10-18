package com.whichonespink.spacex.data.remote.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ShipDto(
    @Json(name = "ship_name") val shipName: String,
    @Json(name = "ship_type") val shipType: String,
    @Json(name = "home_port") val homePort: String,
    @Json(name = "image") val imageUrl: String?,
    @Json(name = "missions") val missions: List<MissionDto>
)
