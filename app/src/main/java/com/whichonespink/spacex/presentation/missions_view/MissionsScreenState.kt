package com.whichonespink.spacex.presentation.missions_view

import com.whichonespink.spacex.domain.model.Launch

data class MissionsScreenState(
    //val missions: List<Mission> = emptyList(),
    val launches: List<Launch> = emptyList(),
    val showDialog: Boolean = false,
    val queryLaunchList: List<Launch> = emptyList(),
    val isContentLoading: Boolean = false,
)
