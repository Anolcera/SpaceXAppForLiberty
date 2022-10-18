package com.whichonespink.spacex.data.remote.dto

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class LaunchLinksDto(
    val article_link: String,
    val flickr_images: List<String>,
    val mission_patch: String,
    val mission_patch_small: String,
    val presskit: String?,
    val reddit_campaign: String?,
    val reddit_launch: String?,
    val reddit_media: String?,
    val reddit_recovery: String?,
    val video_link: String,
    val wikipedia: String,
)