package com.whichonespink.spacex.data.mapper

import android.net.Uri
import androidx.core.net.toUri
import com.whichonespink.spacex.data.remote.dto.LaunchDto
import com.whichonespink.spacex.data.remote.dto.LaunchLinksDto
import com.whichonespink.spacex.domain.model.Launch
import com.whichonespink.spacex.domain.model.LaunchLinks

/**
 * Mapper functions to transform data layer classes into domain layer classes
 */


fun LaunchLinksDto.toLaunchLinks(): LaunchLinks{

    val flickrImageUris = flickr_images.map {
        generateUri(it)
    }

    return LaunchLinks(
        article_link = generateUri(article_link),
        flickr_images  = flickrImageUris,
        mission_patch = generateUri(mission_patch),
        mission_patch_small = generateUri(mission_patch_small),
        video_link = generateUri(video_link),
        wikipedia =  generateUri(wikipedia),
        presskit = presskit?.let {
            generateUri(it)
        },
        reddit_campaign = reddit_campaign?.let {
            generateUri(it)
        },
        reddit_launch = reddit_launch?.let {
            generateUri(it)
        },
        reddit_media = reddit_media?.let {
            generateUri(it)
        },
        reddit_recovery = reddit_recovery?.let {
            generateUri(it)
        },

    )
}


fun LaunchDto.toLaunch(): Launch {
    return Launch(
        missionName = missionName,
        launchYear = launchYear,
        details = details ?: "",
        links = links.toLaunchLinks()
    )
}

//transforms target String type into Uri type
private fun generateUri(target: String): Uri {
    return target.toUri().buildUpon().scheme("https").build()
}