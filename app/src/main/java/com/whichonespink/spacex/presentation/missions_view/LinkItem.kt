package com.whichonespink.spacex.presentation.missions_view

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun LinkItem(
    linkName: String,
    link: Uri
){
    val context = LocalContext.current
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Text(
            text = linkName,
            textAlign = TextAlign.Center
        )

        Text(
            text = link.toString(),
            textAlign = TextAlign.Center,
            modifier = Modifier
                .clickable {
                    val intent = Intent(Intent.ACTION_VIEW, link)
                    context.startActivity(intent)

                }
        )
    }
}