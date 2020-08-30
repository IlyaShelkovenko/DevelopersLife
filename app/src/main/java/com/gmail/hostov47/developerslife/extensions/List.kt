/**
 * Created by Ilia Shelkovenko on 30.08.2020.
 */
package com.gmail.hostov47.developerslife.extensions

import com.gmail.hostov47.developerslife.data.local.database.DatabaseHotGif
import com.gmail.hostov47.developerslife.data.local.database.DatabaseLatestGif
import com.gmail.hostov47.developerslife.data.local.database.DatabaseTopGif
import com.gmail.hostov47.developerslife.data.local.domain.Gif
import com.gmail.hostov47.developerslife.data.remote.res.GifRes

fun List<DatabaseLatestGif>.fromLatestToDomainModel(): List<Gif> {
    return map {
        Gif(
            id = it.id,
            description = it.description,
            votes = it.votes ,
            author = it.author,
            date = it.date,
            gifURL = it.gifURL,
            gifSize = it.gifSize,
            previewURL = it.previewURL,
            videoURL = it.videoURL,
            videoPath = it.videoPath,
            videoSize = it.videoSize,
            type = it.type,
            width = it.width,
            height = it.height,
            commentsCount = it.commentsCount,
            fileSize = it.fileSize,
            canVote = it.canVote
        )
    }
}

fun List<DatabaseTopGif>.fromTopToDomainModel(): List<Gif> {
    return map {
        Gif(
            id = it.id,
            description = it.description,
            votes = it.votes ,
            author = it.author,
            date = it.date,
            gifURL = it.gifURL,
            gifSize = it.gifSize,
            previewURL = it.previewURL,
            videoURL = it.videoURL,
            videoPath = it.videoPath,
            videoSize = it.videoSize,
            type = it.type,
            width = it.width,
            height = it.height,
            commentsCount = it.commentsCount,
            fileSize = it.fileSize,
            canVote = it.canVote
        )
    }
}
fun List<DatabaseHotGif>.fromHotToDomainModel(): List<Gif> {
    return map {
        Gif(
            id = it.id,
            description = it.description,
            votes = it.votes ,
            author = it.author,
            date = it.date,
            gifURL = it.gifURL,
            gifSize = it.gifSize,
            previewURL = it.previewURL,
            videoURL = it.videoURL,
            videoPath = it.videoPath,
            videoSize = it.videoSize,
            type = it.type,
            width = it.width,
            height = it.height,
            commentsCount = it.commentsCount,
            fileSize = it.fileSize,
            canVote = it.canVote
        )
    }
}


fun List<GifRes>.asDatabaseModelLatest(): List<DatabaseLatestGif> {
    return map {
        DatabaseLatestGif(
            id = it.id,
            description = it.description,
            votes = it.votes ,
            author = it.author,
            date = it.date,
            gifURL = it.gifURL,
            gifSize = it.gifSize,
            previewURL = it.previewURL,
            videoURL = it.videoURL,
            videoPath = it.videoPath,
            videoSize = it.videoSize,
            type = it.type,
            width = it.width,
            height = it.height,
            commentsCount = it.commentsCount,
            fileSize = it.fileSize,
            canVote = it.canVote
        )
    }
}
fun List<GifRes>.asDatabaseModelTop(): List<DatabaseTopGif> {
    return map {
        DatabaseTopGif(
            id = it.id,
            description = it.description,
            votes = it.votes ,
            author = it.author,
            date = it.date,
            gifURL = it.gifURL,
            gifSize = it.gifSize,
            previewURL = it.previewURL,
            videoURL = it.videoURL,
            videoPath = it.videoPath,
            videoSize = it.videoSize,
            type = it.type,
            width = it.width,
            height = it.height,
            commentsCount = it.commentsCount,
            fileSize = it.fileSize,
            canVote = it.canVote
        )
    }
}
fun List<GifRes>.asDatabaseModelHot(): List<DatabaseHotGif> {
    return map {
        DatabaseHotGif(
            id = it.id,
            description = it.description,
            votes = it.votes ,
            author = it.author,
            date = it.date,
            gifURL = it.gifURL,
            gifSize = it.gifSize,
            previewURL = it.previewURL,
            videoURL = it.videoURL,
            videoPath = it.videoPath,
            videoSize = it.videoSize,
            type = it.type,
            width = it.width,
            height = it.height,
            commentsCount = it.commentsCount,
            fileSize = it.fileSize,
            canVote = it.canVote
        )
    }
}
