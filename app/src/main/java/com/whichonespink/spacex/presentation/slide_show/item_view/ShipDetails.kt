package com.whichonespink.spacex.presentation.slide_show.item_view

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp


@Composable
fun ShipDetails(
    shipName: String,
    shipType: String,
    homePort: String,
    modifier: Modifier = Modifier
){
    Column(
        verticalArrangement = Arrangement.SpaceEvenly,
        modifier = modifier
            .fillMaxWidth()
    ){
        Text(
            text = shipName,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
        )

        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "Ship type",
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.padding(20.dp))

            Text(
                text = shipType,
                textAlign = TextAlign.Center
            )
        }

        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "Home Port",
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.padding(20.dp))

            Text(
                text = homePort,
                textAlign = TextAlign.Center
            )
        }
    }
}