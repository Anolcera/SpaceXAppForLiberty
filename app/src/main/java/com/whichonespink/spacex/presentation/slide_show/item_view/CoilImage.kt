package com.whichonespink.spacex.presentation.slide_show.item_view

import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.annotation.ExperimentalCoilApi
import coil.compose.ImagePainter
import coil.compose.rememberImagePainter
import com.whichonespink.spacex.R

@OptIn(ExperimentalCoilApi::class)
@Composable
fun CoilImage(
    imageUri: Uri?,
    modifier: Modifier = Modifier,
    onImageClick: () -> Unit,
){
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(340.dp)
            .clickable {
                onImageClick()
            },
        contentAlignment = Alignment.Center
    ){
        var painter = rememberImagePainter(
            data = imageUri,
        )

        val painterState = painter.state
        if (painterState is ImagePainter.State.Error){
            painter = rememberImagePainter(
                data = stringResource(R.string.placeholder_image_url),
                builder = {
                    error(R.drawable.ic_broken_image)
                }
            )
        }else if(painterState is ImagePainter.State.Loading){
            CircularProgressIndicator()
        }

        Image(painter = painter, contentDescription = stringResource(R.string.ship_image))
    }
}