package com.whichonespink.spacex.presentation.slide_show

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.google.gson.Gson
import com.whichonespink.spacex.presentation.navigation.SpaceXScreens
import com.whichonespink.spacex.presentation.slide_show.item_view.ShipItemView
import kotlinx.coroutines.delay

@Composable
fun ShipsSlideShowScreen(
    viewModel: SlideShowViewModel = hiltViewModel(),
    navController: NavController
){
    val state = viewModel.state
    val listState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        LazyRow(
            state = listState,
            modifier = Modifier
                .weight(0.8f)
        ){
            items(state.ships.size) { index ->
                val currentShip = state.ships[index]
                Box(
                    modifier = Modifier
                        .fillParentMaxHeight()
                        .fillParentMaxWidth()
                ){
                    ShipItemView(
                        imageUri = currentShip.imageUrl,
                        shipName = currentShip.name,
                        shipType = currentShip.type,
                        homePort = currentShip.homePort,
                        onImageClick = {
                            val flightNumberList = currentShip.missions.map {
                                it.flightNumber
                            }
                            val missionsToJson = Gson().toJson(flightNumberList)
                            navController.navigate(
                                SpaceXScreens.MissionsScreen.passMissionsAsArgs(
                                    missions = missionsToJson
                                )
                            )
                        }
                    )
                }

            }
        }

        SlideShowControl(
            onStartButtonClick = {
                Log.d("SlideShowViewModel-TAG", "onStartButtonClick")
                viewModel.playSlideShow()
            },
            onPauseButtonClick = {
                Log.d("SlideShowViewModel-TAG", "onPauseButtonClick")
                viewModel.pauseSlideShow()
            },
            onReplayButtonClick = {
                Log.d("SlideShowViewModel-TAG", "onReplayButtonClick")
                viewModel.replaySlideShow()
            },
            onPlaybackButtonClick = {
                Log.d("SlideShowViewModel-TAG", "onPlaybackButtonClick")
                viewModel.updatePlaybackSpeed()
            }
        )

        LaunchedEffect(key1 = state.slideShowPlay, key2 = state.slideShowPage){
            Log.d("SlideShowViewModel-TAG", "LaunchedEffect Launched")
            if(state.slideShowPlay && state.slideShowPage < state.ships.size){
                listState.animateScrollToItem(
                    index = state.slideShowPage,
                )
                delay(state.slideShowPlaybackSpeed.speed)
                viewModel.updatePage()
            }else if(state.restartSlideShow){
                listState.animateScrollToItem(
                    index = 0,
                )
                viewModel.removeRestartFlag()
            }
        }
    }
}