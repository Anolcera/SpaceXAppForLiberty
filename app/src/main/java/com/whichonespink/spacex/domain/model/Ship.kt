package com.whichonespink.spacex.domain.model

import android.net.Uri

data class Ship(
    val name: String,
    val type: String,
    val homePort: String,
    val imageUrl: Uri?,
    val missions: List<Mission>
)