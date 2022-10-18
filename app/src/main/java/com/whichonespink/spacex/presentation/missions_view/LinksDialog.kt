package com.whichonespink.spacex.presentation.missions_view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.window.Dialog
import com.whichonespink.spacex.domain.model.LaunchLinks

@Composable
fun LinksDialog(
    links: LaunchLinks,
    //dismissRequest: Boolean,
    onDismissRequest : () -> Unit,
){

    //val dismissRequest = remember{mutableStateOf(dismissRequest)}
    Dialog(onDismissRequest = {onDismissRequest()}) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                //.padding(4.dp)
                .background(MaterialTheme.colors.surface)
        ) {
            Column(
                modifier = Modifier
                    //.fillMaxSize()
                    .weight(0.7f)
            ) {
                Text(text = "Links")
                LazyColumn(
                ){
                    item { LinkItem(linkName = "Mission Patch:", link = links.mission_patch) }
                    item { LinkItem(linkName = "Mission Patch Small:", link = links.mission_patch_small) }
                    item { LinkItem(linkName = "Article Link:", link = links.article_link) }
                    item { LinkItem(linkName = "Video Link:", link = links.video_link) }
                    item { LinkItem(linkName = "Wikipedia:", link = links.wikipedia) }

                    links.presskit?.let {
                        item { LinkItem(linkName = "Presskit:", link = it) }
                    }
                    links.reddit_campaign?.let {
                        item { LinkItem(linkName = "Reddit Campaign:", link = it) }
                    }
                    links.reddit_launch?.let {
                        item { LinkItem(linkName = "Reddit Launch:", link = it) }
                    }
                    links.reddit_media?.let {
                        item { LinkItem(linkName = "Reddit Media:", link = it) }
                    }
                    links.reddit_recovery?.let {
                        item { LinkItem(linkName = "Reddit Recovery:", link = it) }
                    }
                }
            }

            if(links.flickr_images.isNotEmpty()){
                Column(
                    modifier = Modifier
                        //.fillMaxSize()
                        .weight(0.3f)
                ) {
                    Text(text = "Flickr Links")
                    LazyColumn(

                    ){
                        items(links.flickr_images.size){ index ->
                            LinkItem(linkName = "", link = links.flickr_images[index])
                        }
                    }
                }
            }
        }
    }
}