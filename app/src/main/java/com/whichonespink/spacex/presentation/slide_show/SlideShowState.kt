package com.whichonespink.spacex.presentation.slide_show

import com.whichonespink.spacex.constants.SlideShowPlaybackSpeeds
import com.whichonespink.spacex.domain.model.Ship

data class SlideShowState(
    val ships: List<Ship> = emptyList(),
    val slideShowPage: Int = 0,
    val slideShowPlay: Boolean = false,
    val restartSlideShow: Boolean = false,
    val slideShowPlaybackSpeed: SlideShowPlaybackSpeeds = SlideShowPlaybackSpeeds.Playback_1x
)
