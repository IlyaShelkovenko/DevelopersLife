/**
 * Created by Ilia Shelkovenko on 30.08.2020.
 */

package com.gmail.hostov47.developerslife.data.local.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.gmail.hostov47.developerslife.data.local.domain.Gif

@Entity(tableName = "databaselatestgif")
data class DatabaseLatestGif(
    @PrimaryKey
    val id: Int,
    val description: String,
    val votes: Int,
    val author: String,
    val date: String,
    val gifURL: String,
    val gifSize: Int,
    val previewURL: String,
    val videoURL: String,
    val videoPath: String,
    val videoSize: Int,
    val type: String,
    val width: Int,
    val height: Int,
    val commentsCount: Int,
    val fileSize: Int,
    val canVote: Boolean
)

@Entity(tableName = "databasehotgif")
data class DatabaseHotGif(
    @PrimaryKey
    val id: Int,
    val description: String,
    val votes: Int,
    val author: String,
    val date: String,
    val gifURL: String,
    val gifSize: Int,
    val previewURL: String,
    val videoURL: String,
    val videoPath: String,
    val videoSize: Int,
    val type: String,
    val width: Int,
    val height: Int,
    val commentsCount: Int,
    val fileSize: Int,
    val canVote: Boolean
)

@Entity(tableName = "databasetopgif")
data class DatabaseTopGif(
    @PrimaryKey
    val id: Int,
    val description: String,
    val votes: Int,
    val author: String,
    val date: String,
    val gifURL: String,
    val gifSize: Int,
    val previewURL: String,
    val videoURL: String,
    val videoPath: String,
    val videoSize: Int,
    val type: String,
    val width: Int,
    val height: Int,
    val commentsCount: Int,
    val fileSize: Int,
    val canVote: Boolean
)


