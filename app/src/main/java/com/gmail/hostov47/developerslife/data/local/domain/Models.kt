/**
 * Created by Ilia Shelkovenko on 30.08.2020.
 */
package com.gmail.hostov47.developerslife.data.local.domain

data class Gif(
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
)  : Comparable<Gif> {
    override fun compareTo(other: Gif): Int {
        return id - other.id
    }

}