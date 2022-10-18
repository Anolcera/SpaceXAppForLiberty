package com.whichonespink.spacex.domain.model

import android.net.Uri

data class LaunchLinks(
    val article_link: Uri,
    val flickr_images: List<Uri>,
    val mission_patch: Uri,
    val mission_patch_small: Uri,
    val presskit: Uri?,
    val reddit_campaign: Uri?,
    val reddit_launch: Uri?,
    val reddit_media: Uri?,
    val reddit_recovery: Uri?,
    val video_link: Uri,
    val wikipedia: Uri,
)
