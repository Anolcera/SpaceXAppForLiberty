package com.whichonespink.spacex.presentation.slide_show

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.plcoding.stockmarketapp.util.Resource
import com.whichonespink.spacex.constants.SlideShowPlaybackSpeeds
import com.whichonespink.spacex.domain.repository.SpaceXRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SlideShowViewModel @Inject constructor(
    private val repository: SpaceXRepository
) : ViewModel() {

    var state by mutableStateOf(SlideShowState())

    init {
        Log.d(TAG, "SlideShowViewModel init block")
        viewModelScope.launch {
            Log.d(TAG, "viewModelScope launched")
            repository.getShips().collect { result ->
                when(result){
                    is Resource.Success ->{
                        Log.d(TAG, "Resource result Successful")
                        result.data?.forEach {
                            it.missions.forEach {
                                Log.d(TAG, "Mission Name ${it.name} : Flight Number ${it.flightNumber}")
                            }

                        }
                        result.data?.let{
                            state = state.copy(
                                ships = it
                            )
                        }
                    }

                    is Resource.Loading -> {Log.d(TAG, "Resource result Loading")}
                    is Resource.Error -> {Log.d(TAG, "Resource result Error: ${result.message}")}
                }

            }
        }
    }

    fun updatePage(){
        val currentPage = state.slideShowPage
        state = state.copy(
            slideShowPage = currentPage + 1
        )
        Log.d(TAG, "updatePage($currentPage) slideshow page ${state.slideShowPage}")
    }

    fun playSlideShow(){
        state = state.copy(
            slideShowPlay = true,
        )
        Log.d(TAG, "playSlideShow() slideshow play ${state.slideShowPlay}")
    }

    fun pauseSlideShow(){
        state = state.copy(
            slideShowPlay = false,
        )
        Log.d(TAG, "pauseSlideShow() slideshow pause ${state.slideShowPlay}")
    }

    fun replaySlideShow(){
        state = state.copy(
            slideShowPage = 0,
            restartSlideShow = true,
        )
        Log.d(TAG, "replaySlideShow() slideshow replay ${state.slideShowPage}")
    }

    fun updatePlaybackSpeed(){
        Log.d(TAG, "updatePlaybackSpeed() current slideshow playbackspeed ${state.slideShowPlaybackSpeed.speed}")
        val currentOrdinal = state.slideShowPlaybackSpeed.ordinal
        if(currentOrdinal == SlideShowPlaybackSpeeds.PlayBack_5x.ordinal){
            state = state.copy(
                slideShowPlaybackSpeed = SlideShowPlaybackSpeeds.Playback_1x
            )
        }else{
            state = state.copy(
                slideShowPlaybackSpeed = SlideShowPlaybackSpeeds.values()[currentOrdinal+1]
            )
        }

        Log.d(TAG, "updatePlaybackSpeed() new slideshow playbackspeed ${state.slideShowPlaybackSpeed.speed}")
    }

    fun removeRestartFlag() {
        state = state.copy(
            restartSlideShow = false
        )
    }

    companion object{
        const val TAG = "SlideShowViewModel-TAG"
    }
}