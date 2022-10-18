package com.whichonespink.spacex.presentation.slide_show.item_view

import android.net.Uri
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ShipItemView(
    imageUri: Uri?,
    shipName: String,
    shipType: String,
    homePort: String,
    onImageClick: () -> Unit,
){
    Column(
        //shape = MaterialTheme.shapes.large,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top,
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
    ) {
        CoilImage(
            imageUri = imageUri,
            modifier = Modifier
                .weight(0.6f),
            onImageClick
        )

        ShipDetails(
            shipName = shipName,
            shipType = shipType,
            homePort = homePort,
            modifier = Modifier
                .weight(0.4f)
        )
    }
}