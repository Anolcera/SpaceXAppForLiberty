package com.whichonespink.spacex.presentation.missions_view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun DetailsItemView(
    missionName: String,
    launchYear: String,
    launchDetails: String,
    modifier : Modifier = Modifier,
    onLinksClick : () -> Unit,
){
    Column(
        verticalArrangement = Arrangement.SpaceEvenly,
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Text(
            text = missionName,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
        )

        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "launch year",
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.padding(20.dp))

            Text(
                text = launchYear,
                textAlign = TextAlign.Center
            )
        }

        Text(
            text = "links",
            color = Color.Cyan,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .clickable {
                    onLinksClick()
                }
        )

        Text(
            text = launchDetails.withSymbols(50),
            textAlign = TextAlign.Center,
            modifier = Modifier
                .padding(top = 4.dp)
        )
    }
}

private fun String.withSymbols(symbolCount: Int): String{
    val tokens = this.split(' ')
    var result:String = ""
    tokens.forEachIndexed { index, symbol ->
        if(index < symbolCount){
            result += symbol
            result += " "
        }
    }

    return result
}