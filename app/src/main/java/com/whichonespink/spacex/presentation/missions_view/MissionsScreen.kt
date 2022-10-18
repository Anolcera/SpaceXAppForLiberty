package com.whichonespink.spacex.presentation.missions_view

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.whichonespink.spacex.domain.model.Launch

@Composable
fun MissionsScreen(
    viewModel: MissionsScreenViewModel = hiltViewModel(),
    navController: NavController
){
    val state = viewModel.state
    val linksIndex = remember{mutableStateOf(0)}

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {

        var query by remember { mutableStateOf(TextFieldValue("")) }
        val itemSize: Int
        val launchList: List<Launch>
        if(query.text.isBlank()){
            itemSize = state.launches.size
            launchList = state.launches
        }else{
            itemSize = state.queryLaunchList.size
            launchList = state.queryLaunchList

            if(itemSize == 0){
                Text(
                    text = "No Results Found For ${query.text}",
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }

        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth(),
            value = query,
            onValueChange = {
                query = it
                viewModel.searchQueryInLaunches(query.text)
            },
            placeholder = {
                Text(text = "Search...",)
            },
            singleLine = true,
        )

        LazyColumn(){
            items(itemSize){index ->

                if (state.launches.isEmpty()){
                    Toast.makeText(
                        LocalContext.current,
                        "No Launch Data",
                        Toast.LENGTH_SHORT
                    ).show()
                }


                val currentLaunch = launchList[index]
                DetailsItemView(
                    missionName = currentLaunch.missionName,
                    launchYear = currentLaunch.launchYear,
                    launchDetails = currentLaunch.details,
                    onLinksClick = {
                        viewModel.updateDialogState()
                        linksIndex.value = index
                    }
                )
            }
        }

        if (state.showDialog){
            LinksDialog(
                links = state.launches[linksIndex.value].links,
                onDismissRequest = {
                    viewModel.updateDialogState()
                }
            )
        }
    }
}