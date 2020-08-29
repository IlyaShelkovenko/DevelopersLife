/**
 * Created by Ilia Shelkovenko on 29.08.2020.
 */
package com.gmail.hostov47.developerslife.data.remote.res

import com.squareup.moshi.Json

data class GifRes(
    @Json(name = "id")
    val id: Int,
    @Json(name = "description")
    val description: String,
    @Json(name = "votes")
    val votes: Int,
    @Json(name = "author")
    val author: String,
    @Json(name = "date")
    val date: String,
    @Json(name = "gifURL")
    val gifURL: String,
    @Json(name = "gifSize")
    val gifSize: Int,
    @Json(name = "previewURL")
    val previewURL: String,
    @Json(name = "videoURL")
    val videoURL: String,
    @Json(name = "videoPath")
    val videoPath: String,
    @Json(name = "videoSize")
    val videoSize: Int,
    @Json(name = "type")
    val type: String,
    @Json(name = "width")
    val width: Int,
    @Json(name = "height")
    val height: Int,
    @Json(name = "commentsCount")
    val commentsCount: Int,
    @Json(name = "fileSize")
    val fileSize: Int,
    @Json(name = "canVote")
    val canVote: Boolean
)

data class ApiResponse(
    @Json(name = "result")
    val result: List<GifRes>,
    @Json(name = "totalCount")
    val totalCount: Int
)
