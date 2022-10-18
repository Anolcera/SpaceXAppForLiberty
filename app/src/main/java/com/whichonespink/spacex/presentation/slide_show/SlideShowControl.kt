package com.whichonespink.spacex.presentation.slide_show

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import com.whichonespink.spacex.R

@Composable
fun SlideShowControl(
    onStartButtonClick: () -> Unit,
    onPauseButtonClick: () -> Unit,
    onReplayButtonClick: () -> Unit,
    onPlaybackButtonClick: () -> Unit,

) {
    Row(
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
    ) {
        StartButton {
            onStartButtonClick()
        }

        PauseButton {
            onPauseButtonClick()
        }

        ReplayButton {
            onReplayButtonClick()
        }

        PlaybackButton {
            onPlaybackButtonClick()
        }
    }
}

@Composable
private fun StartButton(
    onStartButtonClick: () -> Unit
){
    Button (
        onClick = {
            onStartButtonClick()
        },
    ){
        Icon(
            imageVector = ImageVector.vectorResource(id = R.drawable.ic_play_slideshow),
            contentDescription = "Play Icon"
        )
    }
}

@Composable
private fun PauseButton(
    onPauseButtonClick: () -> Unit
){
    Button (
        onClick = {
            onPauseButtonClick()
        },
    ){
        Icon(
            imageVector = ImageVector.vectorResource(id = R.drawable.ic_pause_slideshow),
            contentDescription = "Play Icon"
        )
    }
}

@Composable
private fun ReplayButton(
    onReplayButtonClick: () -> Unit
){
    Button (
        onClick = {
            onReplayButtonClick()
        },
    ){
        Icon(
            imageVector = ImageVector.vectorResource(id = R.drawable.ic_replay_slideshow),
            contentDescription = "Play Icon"
        )
    }
}

@Composable
private fun PlaybackButton(
    onPlaybackButtonClick: () -> Unit
){
    Button (
        onClick = {
            onPlaybackButtonClick()
        },
    ){
        Icon(
            imageVector = ImageVector.vectorResource(id = R.drawable.ic_playback_speed),
            contentDescription = "Play Icon"
        )
    }
}
