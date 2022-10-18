package com.whichonespink.spacex.domain.model

data class Launch(
    val missionName: String,
    val launchYear: String,
    val details: String,
    val links: LaunchLinks
)
