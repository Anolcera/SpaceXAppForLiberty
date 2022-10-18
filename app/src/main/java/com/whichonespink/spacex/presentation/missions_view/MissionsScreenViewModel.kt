package com.whichonespink.spacex.presentation.missions_view

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.plcoding.stockmarketapp.util.Resource
import com.whichonespink.spacex.domain.model.Launch
import com.whichonespink.spacex.domain.repository.SpaceXRepository
import com.whichonespink.spacex.presentation.navigation.MISSIONS_NAV_ARG
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MissionsScreenViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val repository: SpaceXRepository
) : ViewModel(){

    private val flightNumbers = checkNotNull(savedStateHandle.get<String>(MISSIONS_NAV_ARG))
    private val launches = mutableStateListOf<Launch>()
    var state by mutableStateOf(MissionsScreenState())

    init {

        viewModelScope.launch {
            val type = object : TypeToken<List<Int>>() {}.type
            val missions = Gson().fromJson<List<Int>>(flightNumbers, type)

            if (missions.isNotEmpty()){
                missions.forEach { mission ->
                    try {
                        repository.getLaunch(mission).collect { result ->
                            when (result) {
                                is Resource.Success -> {
                                    result.data?.let { launches.add(it) }
                                    state = state.copy(
                                        launches = launches
                                    )
                                }

                                is Resource.Loading -> {

                                }
                                is Resource.Error -> {Log.d(TAG, "Launches Error ${result.message}")}
                            }
                        }

                    } catch (e: Exception) {
                        Log.d(TAG, "Error Occured while fetching launches $e")
                    }
                }

//                state = state.copy(
//                    missions = missions
//                )
            }
        }
    }

    fun searchQueryInLaunches(query: String){
        viewModelScope.launch {
            state = state.copy(
                queryLaunchList = state.launches.filter {
                    it.launchYear.lowercase().replace(" ", "").contains(query.lowercase()) ||
                            it.missionName.lowercase().replace(" ", "").contains(query.lowercase()) ||
                            it.details.lowercase().replace(" ", "").contains(query.lowercase())
                }
            )
        }
    }

    fun updateDialogState(){
        val currentState = state.showDialog
        state = state.copy(
            showDialog = !currentState
        )
    }

    companion object{
        const val TAG = "MissionsScreenViewModel-TAG"
    }
}
