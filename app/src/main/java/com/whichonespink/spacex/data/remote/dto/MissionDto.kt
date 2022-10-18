package com.whichonespink.spacex.data.remote.dto

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MissionDto(
    val name: String,
    val flight: Int,
)
