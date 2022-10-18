package com.whichonespink.spacex.data.remote.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class LaunchDto(
    @Json(name = "mission_name") val missionName: String,
    @Json(name = "launch_year") val launchYear: String,
    val details: String?,
    @Json(name = "links") val links: LaunchLinksDto
)